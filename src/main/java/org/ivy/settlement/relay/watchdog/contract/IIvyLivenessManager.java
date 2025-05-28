package org.ivy.settlement.relay.watchdog.contract;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

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
public class IIvyLivenessManager extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_AGAINSTCLAIMS = "againstClaims";

    public static final String FUNC_CLAIM = "claim";

    public static final String FUNC_CLOSECLAIMS = "closeClaims";

    public static final String FUNC_GETCOMMITTEDCLAIM = "getCommittedClaim";

    public static final String FUNC_REQUESTCHALLENGE = "requestChallenge";

    public static final String FUNC_SETTLECLAIMS = "settleClaims";

    public static final String FUNC_SETTLEREWARD = "settleReward";

    public static final String FUNC_WITHDRAWDEPOSIT = "withdrawDeposit";

    public static final Event CHALLENGEREQUESTED_EVENT = new Event("ChallengeRequested", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>(true) {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event CHALLENGESETTLED_EVENT = new Event("ChallengeSettled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>(true) {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event CLAIMSETTLED_EVENT = new Event("ClaimSettled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint8>() {}));
    ;

    public static final Event NEWCLAIM_EVENT = new Event("NewClaim", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint64>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event NEWINVOLVED_EVENT = new Event("NewInvolved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint8>() {}));
    ;

    @Deprecated
    protected IIvyLivenessManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IIvyLivenessManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IIvyLivenessManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IIvyLivenessManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ChallengeRequestedEventResponse> getChallengeRequestedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CHALLENGEREQUESTED_EVENT, transactionReceipt);
        ArrayList<ChallengeRequestedEventResponse> responses = new ArrayList<ChallengeRequestedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ChallengeRequestedEventResponse typedResponse = new ChallengeRequestedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.challengeId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.claimId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.challenger = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.defender = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ChallengeRequestedEventResponse getChallengeRequestedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CHALLENGEREQUESTED_EVENT, log);
        ChallengeRequestedEventResponse typedResponse = new ChallengeRequestedEventResponse();
        typedResponse.log = log;
        typedResponse.challengeId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.claimId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.challenger = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.defender = (String) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<ChallengeRequestedEventResponse> challengeRequestedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getChallengeRequestedEventFromLog(log));
    }

    public Flowable<ChallengeRequestedEventResponse> challengeRequestedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHALLENGEREQUESTED_EVENT));
        return challengeRequestedEventFlowable(filter);
    }

    public static List<ChallengeSettledEventResponse> getChallengeSettledEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CHALLENGESETTLED_EVENT, transactionReceipt);
        ArrayList<ChallengeSettledEventResponse> responses = new ArrayList<ChallengeSettledEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ChallengeSettledEventResponse typedResponse = new ChallengeSettledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.challengeId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.winner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.loser = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.bothWin = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ChallengeSettledEventResponse getChallengeSettledEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CHALLENGESETTLED_EVENT, log);
        ChallengeSettledEventResponse typedResponse = new ChallengeSettledEventResponse();
        typedResponse.log = log;
        typedResponse.challengeId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.winner = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.loser = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.bothWin = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<ChallengeSettledEventResponse> challengeSettledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getChallengeSettledEventFromLog(log));
    }

    public Flowable<ChallengeSettledEventResponse> challengeSettledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHALLENGESETTLED_EVENT));
        return challengeSettledEventFlowable(filter);
    }

    public static List<ClaimSettledEventResponse> getClaimSettledEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CLAIMSETTLED_EVENT, transactionReceipt);
        ArrayList<ClaimSettledEventResponse> responses = new ArrayList<ClaimSettledEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ClaimSettledEventResponse typedResponse = new ClaimSettledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.claimId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.finnalState = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ClaimSettledEventResponse getClaimSettledEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CLAIMSETTLED_EVENT, log);
        ClaimSettledEventResponse typedResponse = new ClaimSettledEventResponse();
        typedResponse.log = log;
        typedResponse.claimId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.finnalState = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<ClaimSettledEventResponse> claimSettledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getClaimSettledEventFromLog(log));
    }

    public Flowable<ClaimSettledEventResponse> claimSettledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLAIMSETTLED_EVENT));
        return claimSettledEventFlowable(filter);
    }

    public static List<NewClaimEventResponse> getNewClaimEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NEWCLAIM_EVENT, transactionReceipt);
        ArrayList<NewClaimEventResponse> responses = new ArrayList<NewClaimEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewClaimEventResponse typedResponse = new NewClaimEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.claimId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.chainId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.height = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.hashVal = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NewClaimEventResponse getNewClaimEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NEWCLAIM_EVENT, log);
        NewClaimEventResponse typedResponse = new NewClaimEventResponse();
        typedResponse.log = log;
        typedResponse.claimId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.chainId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.height = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.hashVal = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
        return typedResponse;
    }

    public Flowable<NewClaimEventResponse> newClaimEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNewClaimEventFromLog(log));
    }

    public Flowable<NewClaimEventResponse> newClaimEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWCLAIM_EVENT));
        return newClaimEventFlowable(filter);
    }

    public static List<NewInvolvedEventResponse> getNewInvolvedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NEWINVOLVED_EVENT, transactionReceipt);
        ArrayList<NewInvolvedEventResponse> responses = new ArrayList<NewInvolvedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewInvolvedEventResponse typedResponse = new NewInvolvedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.claimId = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.identity = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NewInvolvedEventResponse getNewInvolvedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NEWINVOLVED_EVENT, log);
        NewInvolvedEventResponse typedResponse = new NewInvolvedEventResponse();
        typedResponse.log = log;
        typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.claimId = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.identity = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<NewInvolvedEventResponse> newInvolvedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNewInvolvedEventFromLog(log));
    }

    public Flowable<NewInvolvedEventResponse> newInvolvedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWINVOLVED_EVENT));
        return newInvolvedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> againstClaims(List<byte[]> _claimIds, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_AGAINSTCLAIMS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<Bytes32>(
                        Bytes32.class,
                        org.web3j.abi.Utils.typeMap(_claimIds, Bytes32.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> claim(List<BigInteger> _chainIds, List<BigInteger> _heights, List<byte[]> _hashVals, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_CLAIM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<Uint16>(
                        Uint16.class,
                        org.web3j.abi.Utils.typeMap(_chainIds, Uint16.class)),
                new org.web3j.abi.datatypes.DynamicArray<Uint64>(
                        Uint64.class,
                        org.web3j.abi.Utils.typeMap(_heights, Uint64.class)),
                new org.web3j.abi.datatypes.DynamicArray<Bytes32>(
                        Bytes32.class,
                        org.web3j.abi.Utils.typeMap(_hashVals, Bytes32.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> closeClaims(List<UploadedHeader> _uploadeds, List<byte[]> _claimIds) {
        final Function function = new Function(
                FUNC_CLOSECLAIMS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<UploadedHeader>(UploadedHeader.class, _uploadeds), 
                new org.web3j.abi.datatypes.DynamicArray<Bytes32>(
                        Bytes32.class,
                        org.web3j.abi.Utils.typeMap(_claimIds, Bytes32.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, byte[]>> getCommittedClaim(byte[] _claimId) {
        final Function function = new Function(FUNC_GETCOMMITTEDCLAIM, 
                Arrays.<Type>asList(new Bytes32(_claimId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, BigInteger, byte[]>>(function,
                new Callable<Tuple3<BigInteger, BigInteger, byte[]>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, byte[]>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> requestChallenge(byte[] _claimId, String _againster, UploadedHeader _committed) {
        final Function function = new Function(
                FUNC_REQUESTCHALLENGE, 
                Arrays.<Type>asList(new Bytes32(_claimId),
                new Address(160, _againster),
                _committed), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> settleClaims(List<byte[]> _claimIds) {
        final Function function = new Function(
                FUNC_SETTLECLAIMS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<Bytes32>(
                        Bytes32.class,
                        org.web3j.abi.Utils.typeMap(_claimIds, Bytes32.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> settleReward(byte[] _claimId, String _to) {
        final Function function = new Function(
                FUNC_SETTLEREWARD, 
                Arrays.<Type>asList(new Bytes32(_claimId),
                new Address(160, _to)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawDeposit(String _to) {
        final Function function = new Function(
                FUNC_WITHDRAWDEPOSIT, 
                Arrays.<Type>asList(new Address(160, _to)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IIvyLivenessManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyLivenessManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IIvyLivenessManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyLivenessManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IIvyLivenessManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IIvyLivenessManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IIvyLivenessManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IIvyLivenessManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IIvyLivenessManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyLivenessManager.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyLivenessManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyLivenessManager.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<IIvyLivenessManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyLivenessManager.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyLivenessManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyLivenessManager.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class UploadedHeader extends DynamicStruct {
        public BigInteger ivyHeight;

        public byte[] proof;

        public UploadedHeader(BigInteger ivyHeight, byte[] proof) {
            super(new Uint256(ivyHeight),
                    new DynamicBytes(proof));
            this.ivyHeight = ivyHeight;
            this.proof = proof;
        }

        public UploadedHeader(Uint256 ivyHeight, DynamicBytes proof) {
            super(ivyHeight, proof);
            this.ivyHeight = ivyHeight.getValue();
            this.proof = proof.getValue();
        }
    }

    public static class ChallengeRequestedEventResponse extends BaseEventResponse {
        public BigInteger challengeId;

        public byte[] claimId;

        public String challenger;

        public String defender;
    }

    public static class ChallengeSettledEventResponse extends BaseEventResponse {
        public BigInteger challengeId;

        public String winner;

        public String loser;

        public Boolean bothWin;
    }

    public static class ClaimSettledEventResponse extends BaseEventResponse {
        public byte[] claimId;

        public BigInteger finnalState;
    }

    public static class NewClaimEventResponse extends BaseEventResponse {
        public byte[] claimId;

        public BigInteger chainId;

        public BigInteger height;

        public byte[] hashVal;
    }

    public static class NewInvolvedEventResponse extends BaseEventResponse {
        public String addr;

        public byte[] claimId;

        public BigInteger identity;
    }
}
