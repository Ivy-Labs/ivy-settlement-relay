package org.ivy.settlement.relay.watchdog.checker.bsc;

import org.ivy.settlement.ethereum.model.constants.ChainType;
import org.ivy.settlement.infrastructure.codec.borsh.Borsh;
import org.ivy.settlement.relay.watchdog.contract.ChallengeManager;
import org.ivy.settlement.relay.watchdog.checker.Checker;
import org.ivy.settlement.relay.watchdog.constants.ChallengeStep;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.EthLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.web3j.abi.Utils.typeMap;

public class BscChecker implements Checker {

    final ChainType chainType;

    BaseFilter filter;

    public BscChecker(ChainType chainType, BaseFilter filter) {
        this.chainType = chainType;
        this.filter = filter;
    }

    @Override
    public ArrayList<EthLog> readBlockLogs(Long height) {
        FilteredBlock b = filter.readFilteredBlock(height);
        if (b == null) {
            return null;
        }
        return (ArrayList<EthLog>) b.getLogs();
    }

    @Override
    public Boolean canChallenge(Long height) {
        return true;
    }

    @Override
    public Function onChallenge(BaseEventResponse baseEvent) {
        ChallengeManager.PublishedEventResponse event = (ChallengeManager.PublishedEventResponse) baseEvent;
        long step = event.step.longValue();
        if (step == ChallengeStep.PUBLISH1.getStep()) {
            long start = event.dispute.challengeStart.longValue();
            long end = event.dispute.challengeEnd.longValue();
            FilteredBlock bs = filter.readFilteredBlock(start);
            FilteredBlock be = filter.readFilteredBlock(end);
            Header startt = new Header();
            Header endd = new Header();
            HeaderProof proof = new HeaderProof();
//            ChallengeManager.ChallState oldState = new ChallengeManager.ChallState(event.)
            Function function = new Function(
                    ChallengeManager.FUNC_PUBLISHDISPUTES,
                    Arrays.asList(new Uint64(event.challengeIndex),
                            null,
                            new DynamicArray(
                                    DynamicBytes.class,
                                    typeMap(null, DynamicBytes.class)),
                            new DynamicBytes(Borsh.serialize(proof))),
                    Collections.<TypeReference<?>>emptyList());
            return function;
        } else if (step == (ChallengeStep.PUBLISH2.getStep() | ChallengeStep.CHALLENGE.getStep())) {
            // TODO：execute CHALLENGE first, then PUBLISH2 (fork block processing).
            Header start = new Header();
            Header end = new Header();
            HeaderProof proof = new HeaderProof();
            Function function = new Function(
                    ChallengeManager.FUNC_PUBLISHDISPUTES,
                    Arrays.asList(new Uint64(event.challengeIndex),
                            null,
                            new DynamicArray(
                                    DynamicBytes.class,
                                    typeMap(null, DynamicBytes.class)),
                            new DynamicBytes(Borsh.serialize(proof))),
                    Collections.<TypeReference<?>>emptyList());
            return function;
        } else if (step == ChallengeStep.CHALLENGE.getStep()) {
            //TODO: fetch transaction calldata to verify full transaction content.
            String tx = event.log.getTransactionHash();
            Header start = new Header();
            Header end = new Header();
            Function function = new Function(
                    ChallengeManager.FUNC_PUBLISHDISPUTES,
                    Arrays.asList(new Uint64(event.challengeIndex),
                            null,
                            new DynamicArray(
                                    DynamicBytes.class,
                                    typeMap(null, DynamicBytes.class)),
                            new DynamicBytes(Borsh.serialize(end))),
                    Collections.<TypeReference<?>>emptyList());
            return function;
        } else if (step == ChallengeStep.BISECT.getStep()) {
            Long mid = (event.dispute.challengeStart.longValue() + event.dispute.challengeEnd.longValue())/2;
            Header start = new Header();
            Header midHeader = new Header();
            Header end = new Header();
            Function function = new Function(
                    ChallengeManager.FUNC_PUBLISHDISPUTES,
                    Arrays.asList(new Uint64(event.challengeIndex),
                            null,
                            new DynamicArray(
                                    DynamicBytes.class,
                                    typeMap(null, DynamicBytes.class)),
                            new DynamicBytes(Borsh.serialize(end))),
                    Collections.<TypeReference<?>>emptyList());
            return function;
        } else if (step == ChallengeStep.CHOOSE.getStep()) {
            Long position = event.dispute.challengeEnd.longValue();
            Function function = new Function(
                    ChallengeManager.FUNC_PUBLISHDISPUTES,
                    Arrays.asList(new Uint64(event.challengeIndex),
                            null,
                            new DynamicArray(
                                    DynamicBytes.class,
                                    typeMap(null, DynamicBytes.class)),
                            new DynamicBytes(Borsh.serialize(position))),
                    Collections.<TypeReference<?>>emptyList());
            return function;
        } else if (step == ChallengeStep.END.getStep()) {
            return null;
        } else {
            throw new RuntimeException("unKnow step");
        }
    }


}
