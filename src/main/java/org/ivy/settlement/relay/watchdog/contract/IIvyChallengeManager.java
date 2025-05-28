package org.ivy.settlement.relay.watchdog.contract;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.*;
import org.web3j.abi.datatypes.reflection.Parameterized;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.11.1.
 */
@SuppressWarnings("rawtypes")
public class IIvyChallengeManager extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_CREATECHALLENGE = "createChallenge";

    public static final Event CHALLENGEENDED_EVENT = new Event("ChallengeEnded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>(true) {}));
    ;

    public static final Event PUBLISHED_EVENT = new Event("Published", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>(true) {}, new TypeReference<Uint64>(true) {}, new TypeReference<Uint32>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Dispute>() {}));
    ;

    @Deprecated
    protected IIvyChallengeManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IIvyChallengeManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IIvyChallengeManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IIvyChallengeManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ChallengeEndedEventResponse> getChallengeEndedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CHALLENGEENDED_EVENT, transactionReceipt);
        ArrayList<ChallengeEndedEventResponse> responses = new ArrayList<ChallengeEndedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ChallengeEndedEventResponse typedResponse = new ChallengeEndedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.challengeIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ChallengeEndedEventResponse getChallengeEndedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CHALLENGEENDED_EVENT, log);
        ChallengeEndedEventResponse typedResponse = new ChallengeEndedEventResponse();
        typedResponse.log = log;
        typedResponse.challengeIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ChallengeEndedEventResponse> challengeEndedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getChallengeEndedEventFromLog(log));
    }

    public Flowable<ChallengeEndedEventResponse> challengeEndedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHALLENGEENDED_EVENT));
        return challengeEndedEventFlowable(filter);
    }

    public static List<PublishedEventResponse> getPublishedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PUBLISHED_EVENT, transactionReceipt);
        ArrayList<PublishedEventResponse> responses = new ArrayList<PublishedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PublishedEventResponse typedResponse = new PublishedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.challengeIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.challengeSeq = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.step = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.reactor = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.deadline = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.dispute = (Dispute) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PublishedEventResponse getPublishedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PUBLISHED_EVENT, log);
        PublishedEventResponse typedResponse = new PublishedEventResponse();
        typedResponse.log = log;
        typedResponse.challengeIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.challengeSeq = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.step = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.reactor = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.deadline = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.dispute = (Dispute) eventValues.getNonIndexedValues().get(3);
        return typedResponse;
    }

    public Flowable<PublishedEventResponse> publishedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPublishedEventFromLog(log));
    }

    public Flowable<PublishedEventResponse> publishedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PUBLISHED_EVENT));
        return publishedEventFlowable(filter);
    }

    public static class Segment implements Type {

        @Override
        public Object getValue() {
            return null;
        }

        @Override
        public String getTypeAsString() {
            return null;
        }
    }

    public RemoteFunctionCall<TransactionReceipt> createChallenge(BigInteger chainId, List<String> participants, String settleAddress, List<Segment> segments) {
        final Function function = new Function(
                FUNC_CREATECHALLENGE, 
                Arrays.<Type>asList(new Uint16(chainId),
                new org.web3j.abi.datatypes.generated.StaticArray2<Address>(
                        Address.class,
                        org.web3j.abi.Utils.typeMap(participants, Address.class)),
                new Address(160, settleAddress),
                new org.web3j.abi.datatypes.generated.StaticArray2<Segment>(
                        Segment.class,
                        org.web3j.abi.Utils.typeMap(segments, Segment.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IIvyChallengeManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyChallengeManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IIvyChallengeManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyChallengeManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IIvyChallengeManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IIvyChallengeManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IIvyChallengeManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IIvyChallengeManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IIvyChallengeManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyChallengeManager.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyChallengeManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyChallengeManager.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<IIvyChallengeManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyChallengeManager.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyChallengeManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyChallengeManager.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class Dispute extends DynamicStruct {
        public BigInteger chainId;

        public BigInteger challengeStart;

        public BigInteger challengeEnd;

        public List<byte[]> headerStates;

        public byte[] extra;

        public Dispute(BigInteger chainId, BigInteger challengeStart, BigInteger challengeEnd, List<byte[]> headerStates, byte[] extra) {
            super(new Uint16(chainId),
                    new Uint64(challengeStart),
                    new Uint64(challengeEnd),
                    new DynamicArray<Bytes32>(
                            Bytes32.class,
                            org.web3j.abi.Utils.typeMap(headerStates, Bytes32.class)),
                    new Bytes32(extra));
            this.chainId = chainId;
            this.challengeStart = challengeStart;
            this.challengeEnd = challengeEnd;
            this.headerStates = headerStates;
            this.extra = extra;
        }

        public Dispute(Uint16 chainId, Uint64 challengeStart, Uint64 challengeEnd, @Parameterized(type = Bytes32.class) DynamicArray<Bytes32> headerStates, Bytes32 extra) {
            super(chainId, challengeStart, challengeEnd, headerStates, extra);
            this.chainId = chainId.getValue();
            this.challengeStart = challengeStart.getValue();
            this.challengeEnd = challengeEnd.getValue();
            this.headerStates = headerStates.getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
            this.extra = extra.getValue();
        }
    }

    public static class ChallengeEndedEventResponse extends BaseEventResponse {
        public BigInteger challengeIndex;
    }

    public static class PublishedEventResponse extends BaseEventResponse {
        public BigInteger challengeIndex;

        public BigInteger challengeSeq;

        public BigInteger step;

        public String reactor;

        public BigInteger deadline;

        public Dispute dispute;
    }
}
