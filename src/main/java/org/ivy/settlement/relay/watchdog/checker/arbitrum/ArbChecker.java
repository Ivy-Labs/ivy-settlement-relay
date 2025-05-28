//package org.ivy.settlement.relay.watchdog.checker.arbitrum;
//
//
//import org.ivy.settlement.ethereum.model.constants.ChainType;
//import org.ivy.settlement.infrastructure.codec.borsh.Borsh;
//import contract.watchdog.org.ivy.settlement.relay.ChallengeManager;
//import checker.watchdog.org.ivy.settlement.relay.Checker;
//import checker.watchdog.org.ivy.settlement.relay.EVMHeader;
//import constants.watchdog.org.ivy.settlement.relay.ChallengeStep;
//import org.web3j.abi.TypeReference;
//import org.web3j.abi.datatypes.DynamicArray;
//import org.web3j.abi.datatypes.DynamicBytes;
//import org.web3j.abi.datatypes.Function;
//import org.web3j.abi.datatypes.generated.Uint64;
//import org.web3j.protocol.core.methods.response.BaseEventResponse;
//import org.web3j.protocol.core.methods.response.EthLog;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//
//import static org.web3j.abi.Utils.typeMap;
//
//public class ArbChecker implements Checker {
//    final ChainType chainType;
//
//    BaseFilter filter;
//
//    public ArbChecker(ChainType chainType) {
//        this.chainType = chainType;
//    }
//    @Override
//    public ArrayList<EthLog> readBlockLogs(Long height) {
//        FilteredBlock b = filter.readFilteredBlock(height);
//        if (b == null) {
//            return null;
//        }
//        return (ArrayList<EthLog>) b.getLogs();
//    }
//    @Override
//    public Boolean canChallenge(Long height) {
//        return null;
//    }
//
//    @Override
//    public Function onChallenge(BaseEventResponse baseEvent) {
//        ChallengeManager.PublishedEventResponse event = (ChallengeManager.PublishedEventResponse) baseEvent;
//        long step = event.step.longValue();
//        if (step == (ChallengeStep.PUBLISH1.getStep() | SubStep.ARB_HEADER_PUBLISH.getStep())) {
//            Header start = new Header();
//            Header end = new Header();
//            HeaderProof proof = new HeaderProof();
//            return new Function(
//                    ChallengeManager.FUNC_PUBLISHDISPUTES,
//                    Arrays.asList(new Uint64(event.challengeIndex),
//                            null,
//                            new DynamicArray(
//                                    DynamicBytes.class,
//                                    typeMap(null, DynamicBytes.class)),
//                            new DynamicBytes(Borsh.serialize(proof))),
//                    Collections.<TypeReference<?>>emptyList());
//        } else if (step == (ChallengeStep.PUBLISH1.getStep() | SubStep.ARB_CONFIRM_PUBLISH.getStep())){
//            Header start = new Header();
//            Header end = new Header();
//            ConfirmProof proof = new ConfirmProof();
//            return new Function(
//                    ChallengeManager.FUNC_PUBLISHDISPUTES,
//                    Arrays.asList(new Uint64(event.challengeIndex),
//                            null,
//                            new DynamicArray(
//                                    DynamicBytes.class,
//                                    typeMap(null, DynamicBytes.class)),
//                            new DynamicBytes(Borsh.serialize(proof))),
//                    Collections.<TypeReference<?>>emptyList());
//        } else if (step == ChallengeStep.PUBLISH1.getStep()) {
//            EVMHeader start = new EVMHeader();
//            EVMHeader end = new EVMHeader();
//            return new Function(
//                    ChallengeManager.FUNC_PUBLISHDISPUTES,
//                    Arrays.asList(new Uint64(event.challengeIndex),
//                            null,
//                            new DynamicArray(
//                                    DynamicBytes.class,
//                                    typeMap(null, DynamicBytes.class)),
//                            new DynamicBytes(Borsh.serialize(end))),
//                    Collections.<TypeReference<?>>emptyList());
//        } else if (step == ChallengeStep.CHALLENGE.getStep()) {
//            //TODO: invalid step
//            //TODO: fetch tx calldata to validate full content
//            String tx = event.log.getTransactionHash();
//            Header start = new Header();
//            Header end = new Header();
//            return new Function(
//                    ChallengeManager.FUNC_PUBLISHDISPUTES,
//                    Arrays.asList(new Uint64(event.challengeIndex),
//                            null,
//                            new DynamicArray(
//                                    DynamicBytes.class,
//                                    typeMap(null, DynamicBytes.class)),
//                            new DynamicBytes(Borsh.serialize(end))),
//                    Collections.<TypeReference<?>>emptyList());
//        } else if (step == ChallengeStep.BISECT.getStep()) {
//            Long mid = (event.dispute.challengeStart.longValue() + event.dispute.challengeEnd.longValue())/2;
//            EVMHeader start = new EVMHeader();
//            EVMHeader midHeader = new EVMHeader();
//            EVMHeader end = new EVMHeader();
//            return new Function(
//                    ChallengeManager.FUNC_PUBLISHDISPUTES,
//                    Arrays.asList(new Uint64(event.challengeIndex),
//                            null,
//                            new DynamicArray(
//                                    DynamicBytes.class,
//                                    typeMap(null, DynamicBytes.class)),
//                            new DynamicBytes(Borsh.serialize(end))),
//                    Collections.<TypeReference<?>>emptyList());
//        } else if (step == ChallengeStep.CHOOSE.getStep()) {
//            Long position = event.dispute.challengeEnd.longValue();
//            return new Function(
//                    ChallengeManager.FUNC_PUBLISHDISPUTES,
//                    Arrays.asList(new Uint64(event.challengeIndex),
//                            null,
//                            new DynamicArray(
//                                    DynamicBytes.class,
//                                    typeMap(null, DynamicBytes.class)),
//                            new DynamicBytes(Borsh.serialize(position))),
//                    Collections.<TypeReference<?>>emptyList());
//        } else if (step == ChallengeStep.END.getStep()) {
//            return null;
//        } else {
//            throw new RuntimeException("uUnKnow step");
//        }
//
//    }
//
//}
