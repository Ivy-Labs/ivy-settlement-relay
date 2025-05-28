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
public class IIvyNetworkManager extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADDCHAIN = "addChain";

    public static final String FUNC_ADDCHALLENGERSTAKE = "addChallengerStake";

    public static final String FUNC_CALCULATEEPOCH = "calculateEpoch";

    public static final String FUNC_DEREGISTER = "deRegister";

    public static final String FUNC_DELEGATETO = "delegateTo";

    public static final String FUNC_GETCHAINMANAGER = "getChainManager";

    public static final String FUNC_GETCURRENTEPOCH = "getCurrentEpoch";

    public static final String FUNC_GETNODEMANAGER = "getNodeManager";

    public static final String FUNC_GETSIGNERSET = "getSignerSet";

    public static final String FUNC_GETSTAKEPOOLMANAGER = "getStakePoolManager";

    public static final String FUNC_ISCOMMITTEDBLK = "isCommittedBlk";

    public static final String FUNC_ISSIGNER = "isSigner";

    public static final String FUNC_PENALITYANDREWARD = "penalityAndReward";

    public static final String FUNC_REGISTERASNODE = "registerAsNode";

    public static final String FUNC_REMOVECHAIN = "removeChain";

    public static final String FUNC_UNDELEGATE = "undelegate";

    public static final String FUNC_UPDATENODESTATE = "updateNodeState";

    public static final String FUNC_UPLOADIVYBLK = "uploadIvyBlk";

    public static final String FUNC_VALIDATETX = "validateTx";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_WITHDRAWSIGNREWARD = "withdrawSignReward";

    public static final Event NODEDEREGISTERED_EVENT = new Event("NodeDeregistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event NODEREGISTERED_EVENT = new Event("NodeRegistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event NODESHAREDECREASED_EVENT = new Event("NodeShareDecreased", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NODESHAREINCREASED_EVENT = new Event("NodeShareIncreased", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TXVALIDATIONSUCCESS_EVENT = new Event("TxValidationSuccess", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<Uint64>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Uint64>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected IIvyNetworkManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IIvyNetworkManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IIvyNetworkManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IIvyNetworkManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<NodeDeregisteredEventResponse> getNodeDeregisteredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NODEDEREGISTERED_EVENT, transactionReceipt);
        ArrayList<NodeDeregisteredEventResponse> responses = new ArrayList<NodeDeregisteredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NodeDeregisteredEventResponse typedResponse = new NodeDeregisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NodeDeregisteredEventResponse getNodeDeregisteredEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NODEDEREGISTERED_EVENT, log);
        NodeDeregisteredEventResponse typedResponse = new NodeDeregisteredEventResponse();
        typedResponse.log = log;
        typedResponse.node = (String) eventValues.getIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<NodeDeregisteredEventResponse> nodeDeregisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNodeDeregisteredEventFromLog(log));
    }

    public Flowable<NodeDeregisteredEventResponse> nodeDeregisteredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NODEDEREGISTERED_EVENT));
        return nodeDeregisteredEventFlowable(filter);
    }

    public static List<NodeRegisteredEventResponse> getNodeRegisteredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NODEREGISTERED_EVENT, transactionReceipt);
        ArrayList<NodeRegisteredEventResponse> responses = new ArrayList<NodeRegisteredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NodeRegisteredEventResponse typedResponse = new NodeRegisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NodeRegisteredEventResponse getNodeRegisteredEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NODEREGISTERED_EVENT, log);
        NodeRegisteredEventResponse typedResponse = new NodeRegisteredEventResponse();
        typedResponse.log = log;
        typedResponse.node = (String) eventValues.getIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<NodeRegisteredEventResponse> nodeRegisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNodeRegisteredEventFromLog(log));
    }

    public Flowable<NodeRegisteredEventResponse> nodeRegisteredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NODEREGISTERED_EVENT));
        return nodeRegisteredEventFlowable(filter);
    }

    public static List<NodeShareDecreasedEventResponse> getNodeShareDecreasedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NODESHAREDECREASED_EVENT, transactionReceipt);
        ArrayList<NodeShareDecreasedEventResponse> responses = new ArrayList<NodeShareDecreasedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NodeShareDecreasedEventResponse typedResponse = new NodeShareDecreasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.staker = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.shares = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NodeShareDecreasedEventResponse getNodeShareDecreasedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NODESHAREDECREASED_EVENT, log);
        NodeShareDecreasedEventResponse typedResponse = new NodeShareDecreasedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.staker = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.shares = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<NodeShareDecreasedEventResponse> nodeShareDecreasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNodeShareDecreasedEventFromLog(log));
    }

    public Flowable<NodeShareDecreasedEventResponse> nodeShareDecreasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NODESHAREDECREASED_EVENT));
        return nodeShareDecreasedEventFlowable(filter);
    }

    public static List<NodeShareIncreasedEventResponse> getNodeShareIncreasedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NODESHAREINCREASED_EVENT, transactionReceipt);
        ArrayList<NodeShareIncreasedEventResponse> responses = new ArrayList<NodeShareIncreasedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NodeShareIncreasedEventResponse typedResponse = new NodeShareIncreasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.staker = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.shares = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NodeShareIncreasedEventResponse getNodeShareIncreasedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NODESHAREINCREASED_EVENT, log);
        NodeShareIncreasedEventResponse typedResponse = new NodeShareIncreasedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.staker = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.shares = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<NodeShareIncreasedEventResponse> nodeShareIncreasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNodeShareIncreasedEventFromLog(log));
    }

    public Flowable<NodeShareIncreasedEventResponse> nodeShareIncreasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NODESHAREINCREASED_EVENT));
        return nodeShareIncreasedEventFlowable(filter);
    }

    public static List<TxValidationSuccessEventResponse> getTxValidationSuccessEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TXVALIDATIONSUCCESS_EVENT, transactionReceipt);
        ArrayList<TxValidationSuccessEventResponse> responses = new ArrayList<TxValidationSuccessEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TxValidationSuccessEventResponse typedResponse = new TxValidationSuccessEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.srcChainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.ivyHeight = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.srcAddress = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.nonce = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.fee = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TxValidationSuccessEventResponse getTxValidationSuccessEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TXVALIDATIONSUCCESS_EVENT, log);
        TxValidationSuccessEventResponse typedResponse = new TxValidationSuccessEventResponse();
        typedResponse.log = log;
        typedResponse.srcChainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.ivyHeight = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.srcAddress = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.nonce = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.fee = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        return typedResponse;
    }

    public Flowable<TxValidationSuccessEventResponse> txValidationSuccessEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTxValidationSuccessEventFromLog(log));
    }

    public Flowable<TxValidationSuccessEventResponse> txValidationSuccessEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TXVALIDATIONSUCCESS_EVENT));
        return txValidationSuccessEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addChain(BigInteger _chainId, BigInteger _srcAddressSize, BigInteger _srcHeight, String _srcChainLib, String _srcValidationLib, byte[] _srcChainIvy, byte[] _srcGenesis) {
        final Function function = new Function(
                FUNC_ADDCHAIN, 
                Arrays.<Type>asList(new Uint16(_chainId),
                new Uint64(_srcAddressSize),
                new Uint64(_srcHeight),
                new Address(160, _srcChainLib),
                new Address(160, _srcValidationLib),
                new Bytes32(_srcChainIvy),
                new Bytes32(_srcGenesis)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addChallengerStake(String _challenger, BigInteger _token, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_ADDCHALLENGERSTAKE, 
                Arrays.<Type>asList(new Address(160, _challenger),
                new Uint256(_token)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> calculateEpoch(BigInteger _blockNumber) {
        final Function function = new Function(FUNC_CALCULATEEPOCH, 
                Arrays.<Type>asList(new Uint256(_blockNumber)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> deRegister() {
        final Function function = new Function(
                FUNC_DEREGISTER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> delegateTo(String node, BigInteger amount, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_DELEGATETO, 
                Arrays.<Type>asList(new Address(160, node),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<String> getChainManager() {
        final Function function = new Function(FUNC_GETCHAINMANAGER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getCurrentEpoch() {
        final Function function = new Function(FUNC_GETCURRENTEPOCH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getNodeManager() {
        final Function function = new Function(FUNC_GETNODEMANAGER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getSignerSet(BigInteger _ivyHeight) {
        final Function function = new Function(FUNC_GETSIGNERSET, 
                Arrays.<Type>asList(new Uint256(_ivyHeight)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> getStakePoolManager() {
        final Function function = new Function(FUNC_GETSTAKEPOOLMANAGER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isCommittedBlk(BigInteger _ivyHeight) {
        final Function function = new Function(FUNC_ISCOMMITTEDBLK, 
                Arrays.<Type>asList(new Uint256(_ivyHeight)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isSigner(BigInteger _ivyHeight, String _node) {
        final Function function = new Function(FUNC_ISSIGNER, 
                Arrays.<Type>asList(new Uint256(_ivyHeight),
                new Address(160, _node)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> penalityAndReward(BigInteger _loserType, String _loser, String _winner, BigInteger _proportion) {
        final Function function = new Function(
                FUNC_PENALITYANDREWARD, 
                Arrays.<Type>asList(new Uint8(_loserType),
                new Address(160, _loser),
                new Address(160, _winner),
                new Uint8(_proportion)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> registerAsNode(BigInteger amount, Boolean applyVoter, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_REGISTERASNODE, 
                Arrays.<Type>asList(new Uint256(amount),
                new Bool(applyVoter)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> removeChain(BigInteger _chainId) {
        final Function function = new Function(
                FUNC_REMOVECHAIN, 
                Arrays.<Type>asList(new Uint16(_chainId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> undelegate(String staker) {
        final Function function = new Function(
                FUNC_UNDELEGATE, 
                Arrays.<Type>asList(new Address(160, staker)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateNodeState(String _addr, BigInteger _state) {
        final Function function = new Function(
                FUNC_UPDATENODESTATE, 
                Arrays.<Type>asList(new Address(160, _addr),
                new Uint8(_state)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> uploadIvyBlk(IvyBlkInfo _ivyBlkInfo, List<SigInfo> _sigInfos) {
        final Function function = new Function(
                FUNC_UPLOADIVYBLK, 
                Arrays.<Type>asList(_ivyBlkInfo, 
                new DynamicArray<SigInfo>(SigInfo.class, _sigInfos)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> validateTx(BigInteger _srcChainId, BigInteger _ivyHeight, byte[] _proof, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_VALIDATETX, 
                Arrays.<Type>asList(new Uint16(_srcChainId),
                new Uint64(_ivyHeight),
                new DynamicBytes(_proof)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw() {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawSignReward() {
        final Function function = new Function(
                FUNC_WITHDRAWSIGNREWARD, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IIvyNetworkManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyNetworkManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IIvyNetworkManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyNetworkManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IIvyNetworkManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IIvyNetworkManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IIvyNetworkManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IIvyNetworkManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IIvyNetworkManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyNetworkManager.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyNetworkManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyNetworkManager.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<IIvyNetworkManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyNetworkManager.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyNetworkManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyNetworkManager.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class IvyBlkInfo extends DynamicStruct {
        public BigInteger epoch;

        public BigInteger ivyHeight;

        public BigInteger txNums;

        public byte[] combinedHash;

        public byte[] data;

        public IvyBlkInfo(BigInteger epoch, BigInteger ivyHeight, BigInteger txNums, byte[] combinedHash, byte[] data) {
            super(new Uint256(epoch),
                    new Uint256(ivyHeight),
                    new Uint64(txNums),
                    new Bytes32(combinedHash),
                    new DynamicBytes(data));
            this.epoch = epoch;
            this.ivyHeight = ivyHeight;
            this.txNums = txNums;
            this.combinedHash = combinedHash;
            this.data = data;
        }

        public IvyBlkInfo(Uint256 epoch, Uint256 ivyHeight, Uint64 txNums, Bytes32 combinedHash, DynamicBytes data) {
            super(epoch, ivyHeight, txNums, combinedHash, data);
            this.epoch = epoch.getValue();
            this.ivyHeight = ivyHeight.getValue();
            this.txNums = txNums.getValue();
            this.combinedHash = combinedHash.getValue();
            this.data = data.getValue();
        }
    }

    public static class SigInfo extends DynamicStruct {
        public byte[] sig;

        public BigInteger slot;

        public SigInfo(byte[] sig, BigInteger slot) {
            super(new DynamicBytes(sig),
                    new Uint8(slot));
            this.sig = sig;
            this.slot = slot;
        }

        public SigInfo(DynamicBytes sig, Uint8 slot) {
            super(sig, slot);
            this.sig = sig.getValue();
            this.slot = slot.getValue();
        }
    }

    public static class Packet extends DynamicStruct {
        public byte[] relayerId;

        public BigInteger srcChainId;

        public BigInteger dstChainId;

        public BigInteger nonce;

        public String dstAddress;

        public byte[] srcAddress;

        public byte[] ivyAddress;

        public byte[] payload;

        public Packet(byte[] relayerId, BigInteger srcChainId, BigInteger dstChainId, BigInteger nonce, String dstAddress, byte[] srcAddress, byte[] ivyAddress, byte[] payload) {
            super(new Bytes32(relayerId),
                    new Uint16(srcChainId),
                    new Uint16(dstChainId),
                    new Uint64(nonce),
                    new Address(160, dstAddress),
                    new DynamicBytes(srcAddress),
                    new Bytes32(ivyAddress),
                    new DynamicBytes(payload));
            this.relayerId = relayerId;
            this.srcChainId = srcChainId;
            this.dstChainId = dstChainId;
            this.nonce = nonce;
            this.dstAddress = dstAddress;
            this.srcAddress = srcAddress;
            this.ivyAddress = ivyAddress;
            this.payload = payload;
        }

        public Packet(Bytes32 relayerId, Uint16 srcChainId, Uint16 dstChainId, Uint64 nonce, Address dstAddress, DynamicBytes srcAddress, Bytes32 ivyAddress, DynamicBytes payload) {
            super(relayerId, srcChainId, dstChainId, nonce, dstAddress, srcAddress, ivyAddress, payload);
            this.relayerId = relayerId.getValue();
            this.srcChainId = srcChainId.getValue();
            this.dstChainId = dstChainId.getValue();
            this.nonce = nonce.getValue();
            this.dstAddress = dstAddress.getValue();
            this.srcAddress = srcAddress.getValue();
            this.ivyAddress = ivyAddress.getValue();
            this.payload = payload.getValue();
        }
    }

    public static class NodeDeregisteredEventResponse extends BaseEventResponse {
        public String node;
    }

    public static class NodeRegisteredEventResponse extends BaseEventResponse {
        public String node;
    }

    public static class NodeShareDecreasedEventResponse extends BaseEventResponse {
        public String node;

        public String staker;

        public BigInteger shares;
    }

    public static class NodeShareIncreasedEventResponse extends BaseEventResponse {
        public String node;

        public String staker;

        public BigInteger shares;
    }

    public static class TxValidationSuccessEventResponse extends BaseEventResponse {
        public BigInteger srcChainId;

        public BigInteger ivyHeight;

        public byte[] srcAddress;

        public BigInteger nonce;

        public BigInteger fee;
    }
}
