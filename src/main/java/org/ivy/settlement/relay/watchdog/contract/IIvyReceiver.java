package org.ivy.settlement.relay.watchdog.contract;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
public class IIvyReceiver extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_IVYRECEIVE = "ivyReceive";

    public static final Event RECEIVED_EVENT = new Event("Received", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Uint64>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    @Deprecated
    protected IIvyReceiver(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IIvyReceiver(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IIvyReceiver(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IIvyReceiver(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ReceivedEventResponse> getReceivedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RECEIVED_EVENT, transactionReceipt);
        ArrayList<ReceivedEventResponse> responses = new ArrayList<ReceivedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ReceivedEventResponse typedResponse = new ReceivedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.srcChainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.srcAddress = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.nonce = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.payload = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ReceivedEventResponse getReceivedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RECEIVED_EVENT, log);
        ReceivedEventResponse typedResponse = new ReceivedEventResponse();
        typedResponse.log = log;
        typedResponse.srcChainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.srcAddress = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.nonce = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.payload = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
        return typedResponse;
    }

    public Flowable<ReceivedEventResponse> receivedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getReceivedEventFromLog(log));
    }

    public Flowable<ReceivedEventResponse> receivedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECEIVED_EVENT));
        return receivedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> ivyReceive(BigInteger _srcChainId, byte[] _srcAddress, BigInteger _nonce, byte[] _payload) {
        final Function function = new Function(
                FUNC_IVYRECEIVE, 
                Arrays.<Type>asList(new Uint16(_srcChainId),
                new DynamicBytes(_srcAddress),
                new Uint64(_nonce),
                new DynamicBytes(_payload)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IIvyReceiver load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyReceiver(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IIvyReceiver load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyReceiver(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IIvyReceiver load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IIvyReceiver(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IIvyReceiver load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IIvyReceiver(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IIvyReceiver> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyReceiver.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyReceiver> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyReceiver.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<IIvyReceiver> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyReceiver.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyReceiver> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyReceiver.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class ReceivedEventResponse extends BaseEventResponse {
        public BigInteger srcChainId;

        public byte[] srcAddress;

        public BigInteger nonce;

        public byte[] payload;
    }
}
