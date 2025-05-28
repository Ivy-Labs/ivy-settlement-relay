package org.ivy.settlement.relay.watchdog.contract;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class IIvyRelayerManager extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATESRCRELAYER = "activateSrcRelayer";

    public static final String FUNC_PENALITYRELAYER = "penalityRelayer";

    public static final String FUNC_REGISTERDSTRELAYER = "registerDstRelayer";

    public static final String FUNC_SUBMITTX = "submitTx";

    public static final String FUNC_UNACTIVATESRCRELAYER = "unactivateSrcRelayer";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final Event DSTRELAYERREGISTERED_EVENT = new Event("DstRelayerRegistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event PENALITYRELAYER_EVENT = new Event("PenalityRelayer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SRCRELAYERACTIVATED_EVENT = new Event("SrcRelayerActivated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint16>() {}));
    ;

    public static final Event SRCRELAYERUNACTIVATED_EVENT = new Event("SrcRelayerUnactivated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAW_EVENT = new Event("Withdraw", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected IIvyRelayerManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IIvyRelayerManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IIvyRelayerManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IIvyRelayerManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<DstRelayerRegisteredEventResponse> getDstRelayerRegisteredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DSTRELAYERREGISTERED_EVENT, transactionReceipt);
        ArrayList<DstRelayerRegisteredEventResponse> responses = new ArrayList<DstRelayerRegisteredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            DstRelayerRegisteredEventResponse typedResponse = new DstRelayerRegisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.relayerId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.deposit = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.description = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DstRelayerRegisteredEventResponse getDstRelayerRegisteredEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DSTRELAYERREGISTERED_EVENT, log);
        DstRelayerRegisteredEventResponse typedResponse = new DstRelayerRegisteredEventResponse();
        typedResponse.log = log;
        typedResponse.relayerId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.deposit = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.description = (String) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<DstRelayerRegisteredEventResponse> dstRelayerRegisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDstRelayerRegisteredEventFromLog(log));
    }

    public Flowable<DstRelayerRegisteredEventResponse> dstRelayerRegisteredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DSTRELAYERREGISTERED_EVENT));
        return dstRelayerRegisteredEventFlowable(filter);
    }

    public static List<PenalityRelayerEventResponse> getPenalityRelayerEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PENALITYRELAYER_EVENT, transactionReceipt);
        ArrayList<PenalityRelayerEventResponse> responses = new ArrayList<PenalityRelayerEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PenalityRelayerEventResponse typedResponse = new PenalityRelayerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.relayerId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.reciever = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.reward = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PenalityRelayerEventResponse getPenalityRelayerEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PENALITYRELAYER_EVENT, log);
        PenalityRelayerEventResponse typedResponse = new PenalityRelayerEventResponse();
        typedResponse.log = log;
        typedResponse.relayerId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.reciever = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.reward = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<PenalityRelayerEventResponse> penalityRelayerEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPenalityRelayerEventFromLog(log));
    }

    public Flowable<PenalityRelayerEventResponse> penalityRelayerEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PENALITYRELAYER_EVENT));
        return penalityRelayerEventFlowable(filter);
    }

    public static List<SrcRelayerActivatedEventResponse> getSrcRelayerActivatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SRCRELAYERACTIVATED_EVENT, transactionReceipt);
        ArrayList<SrcRelayerActivatedEventResponse> responses = new ArrayList<SrcRelayerActivatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SrcRelayerActivatedEventResponse typedResponse = new SrcRelayerActivatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.relayerId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.srcChainId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SrcRelayerActivatedEventResponse getSrcRelayerActivatedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SRCRELAYERACTIVATED_EVENT, log);
        SrcRelayerActivatedEventResponse typedResponse = new SrcRelayerActivatedEventResponse();
        typedResponse.log = log;
        typedResponse.relayerId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.srcChainId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<SrcRelayerActivatedEventResponse> srcRelayerActivatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSrcRelayerActivatedEventFromLog(log));
    }

    public Flowable<SrcRelayerActivatedEventResponse> srcRelayerActivatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SRCRELAYERACTIVATED_EVENT));
        return srcRelayerActivatedEventFlowable(filter);
    }

    public static List<SrcRelayerUnactivatedEventResponse> getSrcRelayerUnactivatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SRCRELAYERUNACTIVATED_EVENT, transactionReceipt);
        ArrayList<SrcRelayerUnactivatedEventResponse> responses = new ArrayList<SrcRelayerUnactivatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SrcRelayerUnactivatedEventResponse typedResponse = new SrcRelayerUnactivatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.relayerId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.srcChainId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.finalizedTaskNums = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SrcRelayerUnactivatedEventResponse getSrcRelayerUnactivatedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SRCRELAYERUNACTIVATED_EVENT, log);
        SrcRelayerUnactivatedEventResponse typedResponse = new SrcRelayerUnactivatedEventResponse();
        typedResponse.log = log;
        typedResponse.relayerId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.srcChainId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.finalizedTaskNums = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<SrcRelayerUnactivatedEventResponse> srcRelayerUnactivatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSrcRelayerUnactivatedEventFromLog(log));
    }

    public Flowable<SrcRelayerUnactivatedEventResponse> srcRelayerUnactivatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SRCRELAYERUNACTIVATED_EVENT));
        return srcRelayerUnactivatedEventFlowable(filter);
    }

    public static List<WithdrawEventResponse> getWithdrawEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(WITHDRAW_EVENT, transactionReceipt);
        ArrayList<WithdrawEventResponse> responses = new ArrayList<WithdrawEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            WithdrawEventResponse typedResponse = new WithdrawEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.relayerId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.receiver = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static WithdrawEventResponse getWithdrawEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(WITHDRAW_EVENT, log);
        WithdrawEventResponse typedResponse = new WithdrawEventResponse();
        typedResponse.log = log;
        typedResponse.relayerId = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.receiver = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<WithdrawEventResponse> withdrawEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getWithdrawEventFromLog(log));
    }

    public Flowable<WithdrawEventResponse> withdrawEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAW_EVENT));
        return withdrawEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> activateSrcRelayer(BigInteger _srcChainId, BigInteger _ivyHeight, byte[] _proof, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_ACTIVATESRCRELAYER, 
                Arrays.<Type>asList(new Uint16(_srcChainId),
                new org.web3j.abi.datatypes.generated.Uint64(_ivyHeight), 
                new org.web3j.abi.datatypes.DynamicBytes(_proof)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> penalityRelayer(BigInteger _srcChainId, BigInteger _gasLimit, BigInteger _ivyHeight, byte[] _proof, String _reciever, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PENALITYRELAYER, 
                Arrays.<Type>asList(new Uint16(_srcChainId),
                new Uint256(_gasLimit),
                new org.web3j.abi.datatypes.generated.Uint64(_ivyHeight), 
                new org.web3j.abi.datatypes.DynamicBytes(_proof), 
                new Address(160, _reciever)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> registerDstRelayer(String _description, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_REGISTERDSTRELAYER, 
                Arrays.<Type>asList(new Utf8String(_description)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> submitTx(BigInteger _srcChainId, BigInteger _gasLimit, BigInteger _ivyHeight, byte[] _proof, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SUBMITTX, 
                Arrays.<Type>asList(new Uint16(_srcChainId),
                new Uint256(_gasLimit),
                new org.web3j.abi.datatypes.generated.Uint64(_ivyHeight), 
                new org.web3j.abi.datatypes.DynamicBytes(_proof)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> unactivateSrcRelayer(BigInteger _srcChainId, BigInteger _ivyHeight, byte[] _proof, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_UNACTIVATESRCRELAYER, 
                Arrays.<Type>asList(new Uint16(_srcChainId),
                new org.web3j.abi.datatypes.generated.Uint64(_ivyHeight), 
                new org.web3j.abi.datatypes.DynamicBytes(_proof)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(String _to) {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new Address(160, _to)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IIvyRelayerManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyRelayerManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IIvyRelayerManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyRelayerManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IIvyRelayerManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IIvyRelayerManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IIvyRelayerManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IIvyRelayerManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IIvyRelayerManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyRelayerManager.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyRelayerManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyRelayerManager.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<IIvyRelayerManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyRelayerManager.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyRelayerManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyRelayerManager.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class DstRelayerRegisteredEventResponse extends BaseEventResponse {
        public byte[] relayerId;

        public BigInteger deposit;

        public String description;
    }

    public static class PenalityRelayerEventResponse extends BaseEventResponse {
        public byte[] relayerId;

        public String reciever;

        public BigInteger reward;
    }

    public static class SrcRelayerActivatedEventResponse extends BaseEventResponse {
        public byte[] relayerId;

        public BigInteger srcChainId;
    }

    public static class SrcRelayerUnactivatedEventResponse extends BaseEventResponse {
        public byte[] relayerId;

        public BigInteger srcChainId;

        public BigInteger finalizedTaskNums;
    }

    public static class WithdrawEventResponse extends BaseEventResponse {
        public byte[] relayerId;

        public String receiver;

        public BigInteger amount;
    }
}
