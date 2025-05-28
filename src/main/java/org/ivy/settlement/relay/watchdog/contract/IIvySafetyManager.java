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
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
public class IIvySafetyManager extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_CLAIMFAKEBLK = "claimFakeBlk";

    public static final String FUNC_CLAIMLACKEDBLK = "claimLackedBlk";

    public static final String FUNC_CLOSELACKEDBLKCHALLENGE = "closeLackedBlkChallenge";

    public static final String FUNC_REPORTFAULTYBLOCK = "reportFaultyBlock";

    public static final String FUNC_REPORTUNSORTEDBLOCK = "reportUnsortedBlock";

    public static final String FUNC_REQUESTCHALLENGE = "requestChallenge";

    public static final String FUNC_SETTLEREWARD = "settleReward";

    public static final String FUNC_SETTLEWAR = "settleWar";

    public static final Event CHALLENGEREQUESTED_EVENT = new Event("ChallengeRequested", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint64>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event CHALLENGESETTLED_EVENT = new Event("ChallengeSettled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event CHALLENGERCLAIMED_EVENT = new Event("ChallengerClaimed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event FAULTYBLOCKREPORTED_EVENT = new Event("FaultyBlockReported", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Address>>() {}));
    ;

    public static final Event UNSORTEDBLOCKREPORTED_EVENT = new Event("UnsortedBlockReported", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Address>>() {}));
    ;

    public static final Event WARCREATED_EVENT = new Event("WarCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event WARENDED_EVENT = new Event("WarEnded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected IIvySafetyManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IIvySafetyManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IIvySafetyManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IIvySafetyManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ChallengeRequestedEventResponse> getChallengeRequestedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CHALLENGEREQUESTED_EVENT, transactionReceipt);
        ArrayList<ChallengeRequestedEventResponse> responses = new ArrayList<ChallengeRequestedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ChallengeRequestedEventResponse typedResponse = new ChallengeRequestedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.warHeight = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.challengeIndex = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.challenger = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.defender = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ChallengeRequestedEventResponse getChallengeRequestedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CHALLENGEREQUESTED_EVENT, log);
        ChallengeRequestedEventResponse typedResponse = new ChallengeRequestedEventResponse();
        typedResponse.log = log;
        typedResponse.warHeight = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.challengeIndex = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.challenger = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.defender = (String) eventValues.getNonIndexedValues().get(3).getValue();
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
            typedResponse.challengeIndex = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.winner = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.loser = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.bothWin = (Boolean) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ChallengeSettledEventResponse getChallengeSettledEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CHALLENGESETTLED_EVENT, log);
        ChallengeSettledEventResponse typedResponse = new ChallengeSettledEventResponse();
        typedResponse.log = log;
        typedResponse.challengeIndex = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.winner = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.loser = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.bothWin = (Boolean) eventValues.getNonIndexedValues().get(3).getValue();
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

    public static List<ChallengerClaimedEventResponse> getChallengerClaimedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CHALLENGERCLAIMED_EVENT, transactionReceipt);
        ArrayList<ChallengerClaimedEventResponse> responses = new ArrayList<ChallengerClaimedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ChallengerClaimedEventResponse typedResponse = new ChallengerClaimedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.warHeight = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.challenger = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.claimType = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.chainId = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.height = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.hash = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ChallengerClaimedEventResponse getChallengerClaimedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CHALLENGERCLAIMED_EVENT, log);
        ChallengerClaimedEventResponse typedResponse = new ChallengerClaimedEventResponse();
        typedResponse.log = log;
        typedResponse.warHeight = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.challenger = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.claimType = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.chainId = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.height = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.hash = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
        return typedResponse;
    }

    public Flowable<ChallengerClaimedEventResponse> challengerClaimedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getChallengerClaimedEventFromLog(log));
    }

    public Flowable<ChallengerClaimedEventResponse> challengerClaimedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHALLENGERCLAIMED_EVENT));
        return challengerClaimedEventFlowable(filter);
    }

    public static List<FaultyBlockReportedEventResponse> getFaultyBlockReportedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(FAULTYBLOCKREPORTED_EVENT, transactionReceipt);
        ArrayList<FaultyBlockReportedEventResponse> responses = new ArrayList<FaultyBlockReportedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            FaultyBlockReportedEventResponse typedResponse = new FaultyBlockReportedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ivyHeight = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.signers = (List<String>) ((Array) eventValues.getNonIndexedValues().get(1)).getNativeValueCopy();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static FaultyBlockReportedEventResponse getFaultyBlockReportedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(FAULTYBLOCKREPORTED_EVENT, log);
        FaultyBlockReportedEventResponse typedResponse = new FaultyBlockReportedEventResponse();
        typedResponse.log = log;
        typedResponse.ivyHeight = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.signers = (List<String>) ((Array) eventValues.getNonIndexedValues().get(1)).getNativeValueCopy();
        return typedResponse;
    }

    public Flowable<FaultyBlockReportedEventResponse> faultyBlockReportedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getFaultyBlockReportedEventFromLog(log));
    }

    public Flowable<FaultyBlockReportedEventResponse> faultyBlockReportedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FAULTYBLOCKREPORTED_EVENT));
        return faultyBlockReportedEventFlowable(filter);
    }

    public static List<UnsortedBlockReportedEventResponse> getUnsortedBlockReportedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(UNSORTEDBLOCKREPORTED_EVENT, transactionReceipt);
        ArrayList<UnsortedBlockReportedEventResponse> responses = new ArrayList<UnsortedBlockReportedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UnsortedBlockReportedEventResponse typedResponse = new UnsortedBlockReportedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.targetIvyHeight = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.lowerIvyHeight = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.lowerHeaderHeight = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.higherIvyHeight = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.higherHeaderHeight = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.signers = (List<String>) ((Array) eventValues.getNonIndexedValues().get(5)).getNativeValueCopy();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static UnsortedBlockReportedEventResponse getUnsortedBlockReportedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(UNSORTEDBLOCKREPORTED_EVENT, log);
        UnsortedBlockReportedEventResponse typedResponse = new UnsortedBlockReportedEventResponse();
        typedResponse.log = log;
        typedResponse.targetIvyHeight = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.lowerIvyHeight = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.lowerHeaderHeight = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.higherIvyHeight = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.higherHeaderHeight = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.signers = (List<String>) ((Array) eventValues.getNonIndexedValues().get(5)).getNativeValueCopy();
        return typedResponse;
    }

    public Flowable<UnsortedBlockReportedEventResponse> unsortedBlockReportedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getUnsortedBlockReportedEventFromLog(log));
    }

    public Flowable<UnsortedBlockReportedEventResponse> unsortedBlockReportedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UNSORTEDBLOCKREPORTED_EVENT));
        return unsortedBlockReportedEventFlowable(filter);
    }

    public static List<WarCreatedEventResponse> getWarCreatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(WARCREATED_EVENT, transactionReceipt);
        ArrayList<WarCreatedEventResponse> responses = new ArrayList<WarCreatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            WarCreatedEventResponse typedResponse = new WarCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.warHeight = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static WarCreatedEventResponse getWarCreatedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(WARCREATED_EVENT, log);
        WarCreatedEventResponse typedResponse = new WarCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.warHeight = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<WarCreatedEventResponse> warCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getWarCreatedEventFromLog(log));
    }

    public Flowable<WarCreatedEventResponse> warCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WARCREATED_EVENT));
        return warCreatedEventFlowable(filter);
    }

    public static List<WarEndedEventResponse> getWarEndedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(WARENDED_EVENT, transactionReceipt);
        ArrayList<WarEndedEventResponse> responses = new ArrayList<WarEndedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            WarEndedEventResponse typedResponse = new WarEndedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.warheight = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static WarEndedEventResponse getWarEndedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(WARENDED_EVENT, log);
        WarEndedEventResponse typedResponse = new WarEndedEventResponse();
        typedResponse.log = log;
        typedResponse.warheight = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<WarEndedEventResponse> warEndedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getWarEndedEventFromLog(log));
    }

    public Flowable<WarEndedEventResponse> warEndedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WARENDED_EVENT));
        return warEndedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> claimFakeBlk(BigInteger _warHeight, UploadedHeader _target, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_CLAIMFAKEBLK, 
                Arrays.<Type>asList(new Uint256(_warHeight),
                _target), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> claimLackedBlk(BigInteger _warHeight, UploadedHeader _target, LackedHeader _lacked, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_CLAIMLACKEDBLK, 
                Arrays.<Type>asList(new Uint256(_warHeight),
                _target, 
                _lacked), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> closeLackedBlkChallenge(BigInteger _challengeIndex, UploadedHeader _uploadedHeader) {
        final Function function = new Function(
                FUNC_CLOSELACKEDBLKCHALLENGE, 
                Arrays.<Type>asList(new Uint64(_challengeIndex),
                _uploadedHeader), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> reportFaultyBlock(BigInteger _targetIvyHeight, IvyBlock _faultyBlock) {
        final Function function = new Function(
                FUNC_REPORTFAULTYBLOCK, 
                Arrays.<Type>asList(new Uint256(_targetIvyHeight),
                _faultyBlock), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> reportUnsortedBlock(BigInteger _targetIvyHeight, UploadedHeader _lower, UploadedHeader _higher) {
        final Function function = new Function(
                FUNC_REPORTUNSORTEDBLOCK, 
                Arrays.<Type>asList(new Uint256(_targetIvyHeight),
                _lower, 
                _higher), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> requestChallenge(BigInteger _warHeight, String _againster, UploadedHeader _committed) {
        final Function function = new Function(
                FUNC_REQUESTCHALLENGE, 
                Arrays.<Type>asList(new Uint256(_warHeight),
                new Address(160, _againster),
                _committed), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> settleReward(BigInteger _warHeight, String _to) {
        final Function function = new Function(
                FUNC_SETTLEREWARD, 
                Arrays.<Type>asList(new Uint256(_warHeight),
                new Address(160, _to)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> settleWar(BigInteger _warHeight) {
        final Function function = new Function(
                FUNC_SETTLEWAR, 
                Arrays.<Type>asList(new Uint256(_warHeight)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IIvySafetyManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvySafetyManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IIvySafetyManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvySafetyManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IIvySafetyManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IIvySafetyManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IIvySafetyManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IIvySafetyManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IIvySafetyManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvySafetyManager.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvySafetyManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvySafetyManager.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<IIvySafetyManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvySafetyManager.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvySafetyManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvySafetyManager.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class LackedHeader extends DynamicStruct {
        public BigInteger height;

        public byte[] headerHash;

        public byte[] proof;

        public LackedHeader(BigInteger height, byte[] headerHash, byte[] proof) {
            super(new Uint64(height),
                    new Bytes32(headerHash),
                    new DynamicBytes(proof));
            this.height = height;
            this.headerHash = headerHash;
            this.proof = proof;
        }

        public LackedHeader(Uint64 height, Bytes32 headerHash, DynamicBytes proof) {
            super(height, headerHash, proof);
            this.height = height.getValue();
            this.headerHash = headerHash.getValue();
            this.proof = proof.getValue();
        }
    }

    public static class IvyBlock extends DynamicStruct {
        public BigInteger ivyHeight;

        public byte[] blockData;

        public byte[] combinedHash;

        public IvyBlock(BigInteger ivyHeight, byte[] blockData, byte[] combinedHash) {
            super(new Uint256(ivyHeight),
                    new DynamicBytes(blockData),
                    new Bytes32(combinedHash));
            this.ivyHeight = ivyHeight;
            this.blockData = blockData;
            this.combinedHash = combinedHash;
        }

        public IvyBlock(Uint256 ivyHeight, DynamicBytes blockData, Bytes32 combinedHash) {
            super(ivyHeight, blockData, combinedHash);
            this.ivyHeight = ivyHeight.getValue();
            this.blockData = blockData.getValue();
            this.combinedHash = combinedHash.getValue();
        }
    }

    public static class ChallengeRequestedEventResponse extends BaseEventResponse {
        public BigInteger warHeight;

        public BigInteger challengeIndex;

        public String challenger;

        public String defender;
    }

    public static class ChallengeSettledEventResponse extends BaseEventResponse {
        public BigInteger challengeIndex;

        public String winner;

        public String loser;

        public Boolean bothWin;
    }

    public static class ChallengerClaimedEventResponse extends BaseEventResponse {
        public BigInteger warHeight;

        public String challenger;

        public BigInteger claimType;

        public BigInteger chainId;

        public BigInteger height;

        public byte[] hash;
    }

    public static class FaultyBlockReportedEventResponse extends BaseEventResponse {
        public BigInteger ivyHeight;

        public List<String> signers;
    }

    public static class UnsortedBlockReportedEventResponse extends BaseEventResponse {
        public BigInteger targetIvyHeight;

        public BigInteger lowerIvyHeight;

        public BigInteger lowerHeaderHeight;

        public BigInteger higherIvyHeight;

        public BigInteger higherHeaderHeight;

        public List<String> signers;
    }

    public static class WarCreatedEventResponse extends BaseEventResponse {
        public BigInteger warHeight;
    }

    public static class WarEndedEventResponse extends BaseEventResponse {
        public BigInteger warheight;
    }
}
