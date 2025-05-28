package org.ivy.settlement.relay.watchdog.contract;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
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
public class IIvyNodeManager extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_GETJOINNODENUMS = "getJoinNodeNums";

    public static final String FUNC_GETNODESTATE = "getNodeState";

    public static final String FUNC_GETQUITNODENUMS = "getQuitNodeNums";

    public static final String FUNC_GETVOTERSETSIZE = "getVoterSetSize";

    public static final String FUNC_GETVOTERS = "getVoters";

    public static final String FUNC_ISVOTER = "isVoter";

    public static final String FUNC_QETTOTALVOTERWEIGHT = "qetTotalVoterWeight";

    public static final String FUNC_QUERYSLOTINDEX = "querySlotIndex";

    public static final String FUNC_QUERYSLOTOWNER = "querySlotOwner";

    public static final String FUNC_QUERYSLOTOWNERANDWEIGHT = "querySlotOwnerAndWeight";

    public static final String FUNC_UPDATENODE = "updateNode";

    public static final String FUNC_UPDATENODESTATE = "updateNodeState";

    public static final Event NODEADD_EVENT = new Event("NodeAdd", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NODEREMOVE_EVENT = new Event("NodeRemove", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NODESTATEUPDATE_EVENT = new Event("NodeStateUpdate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event STAKE_EVENT = new Event("Stake", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event UNSTAKE_EVENT = new Event("Unstake", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VOTERINFOUPDATE_EVENT = new Event("VoterInfoUpdate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected IIvyNodeManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IIvyNodeManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IIvyNodeManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IIvyNodeManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<NodeAddEventResponse> getNodeAddEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NODEADD_EVENT, transactionReceipt);
        ArrayList<NodeAddEventResponse> responses = new ArrayList<NodeAddEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NodeAddEventResponse typedResponse = new NodeAddEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.joinHeight = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.joinEpoch = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NodeAddEventResponse getNodeAddEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NODEADD_EVENT, log);
        NodeAddEventResponse typedResponse = new NodeAddEventResponse();
        typedResponse.log = log;
        typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.joinHeight = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.joinEpoch = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<NodeAddEventResponse> nodeAddEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNodeAddEventFromLog(log));
    }

    public Flowable<NodeAddEventResponse> nodeAddEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NODEADD_EVENT));
        return nodeAddEventFlowable(filter);
    }

    public static List<NodeRemoveEventResponse> getNodeRemoveEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NODEREMOVE_EVENT, transactionReceipt);
        ArrayList<NodeRemoveEventResponse> responses = new ArrayList<NodeRemoveEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NodeRemoveEventResponse typedResponse = new NodeRemoveEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.quitEpoch = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NodeRemoveEventResponse getNodeRemoveEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NODEREMOVE_EVENT, log);
        NodeRemoveEventResponse typedResponse = new NodeRemoveEventResponse();
        typedResponse.log = log;
        typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.quitEpoch = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<NodeRemoveEventResponse> nodeRemoveEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNodeRemoveEventFromLog(log));
    }

    public Flowable<NodeRemoveEventResponse> nodeRemoveEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NODEREMOVE_EVENT));
        return nodeRemoveEventFlowable(filter);
    }

    public static List<NodeStateUpdateEventResponse> getNodeStateUpdateEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NODESTATEUPDATE_EVENT, transactionReceipt);
        ArrayList<NodeStateUpdateEventResponse> responses = new ArrayList<NodeStateUpdateEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NodeStateUpdateEventResponse typedResponse = new NodeStateUpdateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.state = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NodeStateUpdateEventResponse getNodeStateUpdateEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NODESTATEUPDATE_EVENT, log);
        NodeStateUpdateEventResponse typedResponse = new NodeStateUpdateEventResponse();
        typedResponse.log = log;
        typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.state = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<NodeStateUpdateEventResponse> nodeStateUpdateEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNodeStateUpdateEventFromLog(log));
    }

    public Flowable<NodeStateUpdateEventResponse> nodeStateUpdateEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NODESTATEUPDATE_EVENT));
        return nodeStateUpdateEventFlowable(filter);
    }

    public static List<StakeEventResponse> getStakeEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(STAKE_EVENT, transactionReceipt);
        ArrayList<StakeEventResponse> responses = new ArrayList<StakeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            StakeEventResponse typedResponse = new StakeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.balance = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static StakeEventResponse getStakeEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(STAKE_EVENT, log);
        StakeEventResponse typedResponse = new StakeEventResponse();
        typedResponse.log = log;
        typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.balance = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<StakeEventResponse> stakeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getStakeEventFromLog(log));
    }

    public Flowable<StakeEventResponse> stakeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STAKE_EVENT));
        return stakeEventFlowable(filter);
    }

    public static List<UnstakeEventResponse> getUnstakeEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(UNSTAKE_EVENT, transactionReceipt);
        ArrayList<UnstakeEventResponse> responses = new ArrayList<UnstakeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UnstakeEventResponse typedResponse = new UnstakeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.balance = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.withdrawEpoch = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static UnstakeEventResponse getUnstakeEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(UNSTAKE_EVENT, log);
        UnstakeEventResponse typedResponse = new UnstakeEventResponse();
        typedResponse.log = log;
        typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.balance = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.withdrawEpoch = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        return typedResponse;
    }

    public Flowable<UnstakeEventResponse> unstakeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getUnstakeEventFromLog(log));
    }

    public Flowable<UnstakeEventResponse> unstakeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UNSTAKE_EVENT));
        return unstakeEventFlowable(filter);
    }

    public static List<VoterInfoUpdateEventResponse> getVoterInfoUpdateEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(VOTERINFOUPDATE_EVENT, transactionReceipt);
        ArrayList<VoterInfoUpdateEventResponse> responses = new ArrayList<VoterInfoUpdateEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            VoterInfoUpdateEventResponse typedResponse = new VoterInfoUpdateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.slotIndex = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.voteWeight = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.validEpoch = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.totalVoterWeight = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static VoterInfoUpdateEventResponse getVoterInfoUpdateEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(VOTERINFOUPDATE_EVENT, log);
        VoterInfoUpdateEventResponse typedResponse = new VoterInfoUpdateEventResponse();
        typedResponse.log = log;
        typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.slotIndex = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.voteWeight = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.validEpoch = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.totalVoterWeight = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        return typedResponse;
    }

    public Flowable<VoterInfoUpdateEventResponse> voterInfoUpdateEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getVoterInfoUpdateEventFromLog(log));
    }

    public Flowable<VoterInfoUpdateEventResponse> voterInfoUpdateEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTERINFOUPDATE_EVENT));
        return voterInfoUpdateEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> getJoinNodeNums(BigInteger _epoch) {
        final Function function = new Function(FUNC_GETJOINNODENUMS, 
                Arrays.<Type>asList(new Uint256(_epoch)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getNodeState(String _node) {
        final Function function = new Function(FUNC_GETNODESTATE, 
                Arrays.<Type>asList(new Address(160, _node)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getQuitNodeNums(BigInteger _epoch) {
        final Function function = new Function(FUNC_GETQUITNODENUMS, 
                Arrays.<Type>asList(new Uint256(_epoch)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getVoterSetSize() {
        final Function function = new Function(FUNC_GETVOTERSETSIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> getVoters(BigInteger _epoch) {
        final Function function = new Function(FUNC_GETVOTERS, 
                Arrays.<Type>asList(new Uint256(_epoch)),
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

    public RemoteFunctionCall<Tuple2<Boolean, BigInteger>> isVoter(BigInteger _epoch, String _addr) {
        final Function function = new Function(FUNC_ISVOTER, 
                Arrays.<Type>asList(new Uint256(_epoch),
                new Address(160, _addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Uint8>() {}));
        return new RemoteFunctionCall<Tuple2<Boolean, BigInteger>>(function,
                new Callable<Tuple2<Boolean, BigInteger>>() {
                    @Override
                    public Tuple2<Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<Boolean, BigInteger>(
                                (Boolean) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> qetTotalVoterWeight(BigInteger _epoch) {
        final Function function = new Function(FUNC_QETTOTALVOTERWEIGHT, 
                Arrays.<Type>asList(new Uint256(_epoch)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> querySlotIndex(BigInteger _epoch, String _addr) {
        final Function function = new Function(FUNC_QUERYSLOTINDEX, 
                Arrays.<Type>asList(new Uint256(_epoch),
                new Address(160, _addr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> querySlotOwner(BigInteger _epoch, BigInteger _slotIndex) {
        final Function function = new Function(FUNC_QUERYSLOTOWNER, 
                Arrays.<Type>asList(new Uint256(_epoch),
                new Uint8(_slotIndex)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple2<String, BigInteger>> querySlotOwnerAndWeight(BigInteger _epoch, BigInteger _slotIndex) {
        final Function function = new Function(FUNC_QUERYSLOTOWNERANDWEIGHT, 
                Arrays.<Type>asList(new Uint256(_epoch),
                new Uint8(_slotIndex)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<String, BigInteger>>(function,
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> updateNode(BigInteger _taskType, BigInteger _epoch, String _node, byte[] _taskData) {
        final Function function = new Function(
                FUNC_UPDATENODE, 
                Arrays.<Type>asList(new Uint8(_taskType),
                new Uint256(_epoch),
                new Address(160, _node),
                new org.web3j.abi.datatypes.DynamicBytes(_taskData)), 
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

    @Deprecated
    public static IIvyNodeManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyNodeManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IIvyNodeManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyNodeManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IIvyNodeManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IIvyNodeManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IIvyNodeManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IIvyNodeManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IIvyNodeManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyNodeManager.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyNodeManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyNodeManager.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<IIvyNodeManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyNodeManager.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyNodeManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyNodeManager.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class NodeAddEventResponse extends BaseEventResponse {
        public String addr;

        public BigInteger joinHeight;

        public BigInteger joinEpoch;
    }

    public static class NodeRemoveEventResponse extends BaseEventResponse {
        public String addr;

        public BigInteger quitEpoch;
    }

    public static class NodeStateUpdateEventResponse extends BaseEventResponse {
        public String addr;

        public BigInteger state;
    }

    public static class StakeEventResponse extends BaseEventResponse {
        public String addr;

        public BigInteger amount;

        public BigInteger balance;
    }

    public static class UnstakeEventResponse extends BaseEventResponse {
        public String addr;

        public BigInteger amount;

        public BigInteger balance;

        public BigInteger withdrawEpoch;
    }

    public static class VoterInfoUpdateEventResponse extends BaseEventResponse {
        public String addr;

        public BigInteger slotIndex;

        public BigInteger voteWeight;

        public BigInteger validEpoch;

        public BigInteger totalVoterWeight;
    }
}
