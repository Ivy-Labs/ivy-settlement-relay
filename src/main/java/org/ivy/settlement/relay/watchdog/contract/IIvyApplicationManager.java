package org.ivy.settlement.relay.watchdog.contract;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint64;
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
public class IIvyApplicationManager extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_REGISTERAPP = "registerApp";

    public static final String FUNC_RETRYPAYLOAD = "retryPayload";

    public static final Event ADDAPPLICATION_EVENT = new Event("AddApplication", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event PAYLOADCLEARED_EVENT = new Event("PayloadCleared", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Uint64>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event PAYLOADSTORED_EVENT = new Event("PayloadStored", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Address>() {}, new TypeReference<Uint64>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    @Deprecated
    protected IIvyApplicationManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IIvyApplicationManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IIvyApplicationManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IIvyApplicationManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<AddApplicationEventResponse> getAddApplicationEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ADDAPPLICATION_EVENT, transactionReceipt);
        ArrayList<AddApplicationEventResponse> responses = new ArrayList<AddApplicationEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AddApplicationEventResponse typedResponse = new AddApplicationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._relayer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static AddApplicationEventResponse getAddApplicationEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ADDAPPLICATION_EVENT, log);
        AddApplicationEventResponse typedResponse = new AddApplicationEventResponse();
        typedResponse.log = log;
        typedResponse._relayer = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<AddApplicationEventResponse> addApplicationEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getAddApplicationEventFromLog(log));
    }

    public Flowable<AddApplicationEventResponse> addApplicationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDAPPLICATION_EVENT));
        return addApplicationEventFlowable(filter);
    }

    public static List<PayloadClearedEventResponse> getPayloadClearedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAYLOADCLEARED_EVENT, transactionReceipt);
        ArrayList<PayloadClearedEventResponse> responses = new ArrayList<PayloadClearedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PayloadClearedEventResponse typedResponse = new PayloadClearedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.srcChainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.srcAddress = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.nonce = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.dstAddress = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PayloadClearedEventResponse getPayloadClearedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PAYLOADCLEARED_EVENT, log);
        PayloadClearedEventResponse typedResponse = new PayloadClearedEventResponse();
        typedResponse.log = log;
        typedResponse.srcChainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.srcAddress = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.nonce = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.dstAddress = (String) eventValues.getNonIndexedValues().get(3).getValue();
        return typedResponse;
    }

    public Flowable<PayloadClearedEventResponse> payloadClearedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPayloadClearedEventFromLog(log));
    }

    public Flowable<PayloadClearedEventResponse> payloadClearedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAYLOADCLEARED_EVENT));
        return payloadClearedEventFlowable(filter);
    }

    public static List<PayloadStoredEventResponse> getPayloadStoredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAYLOADSTORED_EVENT, transactionReceipt);
        ArrayList<PayloadStoredEventResponse> responses = new ArrayList<PayloadStoredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PayloadStoredEventResponse typedResponse = new PayloadStoredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.srcChainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.srcAddress = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.dstAddress = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.nonce = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.payload = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.reason = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PayloadStoredEventResponse getPayloadStoredEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PAYLOADSTORED_EVENT, log);
        PayloadStoredEventResponse typedResponse = new PayloadStoredEventResponse();
        typedResponse.log = log;
        typedResponse.srcChainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.srcAddress = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.dstAddress = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.nonce = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.payload = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.reason = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
        return typedResponse;
    }

    public Flowable<PayloadStoredEventResponse> payloadStoredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPayloadStoredEventFromLog(log));
    }

    public Flowable<PayloadStoredEventResponse> payloadStoredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAYLOADSTORED_EVENT));
        return payloadStoredEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> registerApp(String _relayer) {
        final Function function = new Function(
                FUNC_REGISTERAPP, 
                Arrays.<Type>asList(new Address(160, _relayer)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> retryPayload(BigInteger _srcChainId, byte[] _srcAddress, byte[] _payload) {
        final Function function = new Function(
                FUNC_RETRYPAYLOAD, 
                Arrays.<Type>asList(new Uint16(_srcChainId),
                new DynamicBytes(_srcAddress),
                new DynamicBytes(_payload)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IIvyApplicationManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyApplicationManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IIvyApplicationManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyApplicationManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IIvyApplicationManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IIvyApplicationManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IIvyApplicationManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IIvyApplicationManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IIvyApplicationManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyApplicationManager.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyApplicationManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyApplicationManager.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<IIvyApplicationManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyApplicationManager.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyApplicationManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyApplicationManager.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class AddApplicationEventResponse extends BaseEventResponse {
        public String _relayer;
    }

    public static class PayloadClearedEventResponse extends BaseEventResponse {
        public BigInteger srcChainId;

        public byte[] srcAddress;

        public BigInteger nonce;

        public String dstAddress;
    }

    public static class PayloadStoredEventResponse extends BaseEventResponse {
        public BigInteger srcChainId;

        public byte[] srcAddress;

        public String dstAddress;

        public BigInteger nonce;

        public byte[] payload;

        public byte[] reason;
    }
}
