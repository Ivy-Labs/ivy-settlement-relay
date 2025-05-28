package org.ivy.settlement.relay.watchdog.contract;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class IIvyChainManager extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADDCHAIN = "addChain";

    public static final String FUNC_ADDCHAINCONFIG = "addChainConfig";

    public static final String FUNC_ADDIVYBLOCK = "addIvyBlock";

    public static final String FUNC_CHALLENGEABLE = "challengeable";

    public static final String FUNC_CHECKCHAINVERISON = "checkChainVerison";

    public static final String FUNC_GETBLKFEEINFO = "getBlkFeeInfo";

    public static final String FUNC_GETBLKROOT = "getBlkRoot";

    public static final String FUNC_GETBLKSIGNERSET = "getBlkSignerSet";

    public static final String FUNC_GETBLKUPLOADTIME = "getBlkUploadTime";

    public static final String FUNC_GETCHAINLIB = "getChainLib";

    public static final String FUNC_GETDEFAULTHEADER = "getDefaultHeader";

    public static final String FUNC_GETLATESTVALIDHEIGHT = "getLatestValidHeight";

    public static final String FUNC_GETVALIDHEADER = "getValidHeader";

    public static final String FUNC_ISIVYBLOCKCHALLENGEABLE = "isIvyBlockChallengeable";

    public static final String FUNC_REMOVECHAIN = "removeChain";

    public static final String FUNC_SETCHALLENGEABLE = "setChallengeable";

    public static final String FUNC_VALIDATEIVYBLOCK = "validateIvyBlock";

    public static final String FUNC_VALIDATETXPROOFBYHEADER = "validateTxProofByHeader";

    public static final String FUNC_VALIDATETXPROOFBYIVY = "validateTxProofByIvy";

    public static final Event ADDCHAIN_EVENT = new Event("AddChain", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<Uint64>() {}, new TypeReference<Uint64>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event ADDCHAINCONFIG_EVENT = new Event("AddChainConfig", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<Uint64>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event ADDIVYBLOCK_EVENT = new Event("AddIvyBlock", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>(true) {}, new TypeReference<Bytes32>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event EMPTYBLK_EVENT = new Event("EmptyBlk", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event REMOVECHAIN_EVENT = new Event("RemoveChain", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
    ;

    public static final Event SETCHALLENGEABLE_EVENT = new Event("SetChallengeable", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event UPLOADSUCCESS_EVENT = new Event("UploadSuccess", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint64>() {}, new TypeReference<Bytes32>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected IIvyChainManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IIvyChainManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IIvyChainManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IIvyChainManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<AddChainEventResponse> getAddChainEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ADDCHAIN_EVENT, transactionReceipt);
        ArrayList<AddChainEventResponse> responses = new ArrayList<AddChainEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AddChainEventResponse typedResponse = new AddChainEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.chainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.srcAddressSize = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.srcHeight = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.srcChainLib = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.srcValidationLib = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.srcChainIvy = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.srcGenesis = (byte[]) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static AddChainEventResponse getAddChainEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ADDCHAIN_EVENT, log);
        AddChainEventResponse typedResponse = new AddChainEventResponse();
        typedResponse.log = log;
        typedResponse.chainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.srcAddressSize = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.srcHeight = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.srcChainLib = (String) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.srcValidationLib = (String) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.srcChainIvy = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
        typedResponse.srcGenesis = (byte[]) eventValues.getNonIndexedValues().get(6).getValue();
        return typedResponse;
    }

    public Flowable<AddChainEventResponse> addChainEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getAddChainEventFromLog(log));
    }

    public Flowable<AddChainEventResponse> addChainEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDCHAIN_EVENT));
        return addChainEventFlowable(filter);
    }

    public static List<AddChainConfigEventResponse> getAddChainConfigEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ADDCHAINCONFIG_EVENT, transactionReceipt);
        ArrayList<AddChainConfigEventResponse> responses = new ArrayList<AddChainConfigEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AddChainConfigEventResponse typedResponse = new AddChainConfigEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.chainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.srcHeight = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.srcChainLib = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.srcGenesis = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static AddChainConfigEventResponse getAddChainConfigEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ADDCHAINCONFIG_EVENT, log);
        AddChainConfigEventResponse typedResponse = new AddChainConfigEventResponse();
        typedResponse.log = log;
        typedResponse.chainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.srcHeight = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.srcChainLib = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.srcGenesis = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
        return typedResponse;
    }

    public Flowable<AddChainConfigEventResponse> addChainConfigEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getAddChainConfigEventFromLog(log));
    }

    public Flowable<AddChainConfigEventResponse> addChainConfigEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDCHAINCONFIG_EVENT));
        return addChainConfigEventFlowable(filter);
    }

    public static List<AddIvyBlockEventResponse> getAddIvyBlockEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ADDIVYBLOCK_EVENT, transactionReceipt);
        ArrayList<AddIvyBlockEventResponse> responses = new ArrayList<AddIvyBlockEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AddIvyBlockEventResponse typedResponse = new AddIvyBlockEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ivyHeight = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.combinedRoot = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static AddIvyBlockEventResponse getAddIvyBlockEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ADDIVYBLOCK_EVENT, log);
        AddIvyBlockEventResponse typedResponse = new AddIvyBlockEventResponse();
        typedResponse.log = log;
        typedResponse.ivyHeight = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.combinedRoot = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<AddIvyBlockEventResponse> addIvyBlockEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getAddIvyBlockEventFromLog(log));
    }

    public Flowable<AddIvyBlockEventResponse> addIvyBlockEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDIVYBLOCK_EVENT));
        return addIvyBlockEventFlowable(filter);
    }

    public static List<EmptyBlkEventResponse> getEmptyBlkEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(EMPTYBLK_EVENT, transactionReceipt);
        ArrayList<EmptyBlkEventResponse> responses = new ArrayList<EmptyBlkEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            EmptyBlkEventResponse typedResponse = new EmptyBlkEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.epoch = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.height = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.root = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static EmptyBlkEventResponse getEmptyBlkEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(EMPTYBLK_EVENT, log);
        EmptyBlkEventResponse typedResponse = new EmptyBlkEventResponse();
        typedResponse.log = log;
        typedResponse.epoch = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.height = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.root = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
        return typedResponse;
    }

    public Flowable<EmptyBlkEventResponse> emptyBlkEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getEmptyBlkEventFromLog(log));
    }

    public Flowable<EmptyBlkEventResponse> emptyBlkEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EMPTYBLK_EVENT));
        return emptyBlkEventFlowable(filter);
    }

    public static List<RemoveChainEventResponse> getRemoveChainEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(REMOVECHAIN_EVENT, transactionReceipt);
        ArrayList<RemoveChainEventResponse> responses = new ArrayList<RemoveChainEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RemoveChainEventResponse typedResponse = new RemoveChainEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.chainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RemoveChainEventResponse getRemoveChainEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(REMOVECHAIN_EVENT, log);
        RemoveChainEventResponse typedResponse = new RemoveChainEventResponse();
        typedResponse.log = log;
        typedResponse.chainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<RemoveChainEventResponse> removeChainEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRemoveChainEventFromLog(log));
    }

    public Flowable<RemoveChainEventResponse> removeChainEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REMOVECHAIN_EVENT));
        return removeChainEventFlowable(filter);
    }

    public static List<SetChallengeableEventResponse> getSetChallengeableEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SETCHALLENGEABLE_EVENT, transactionReceipt);
        ArrayList<SetChallengeableEventResponse> responses = new ArrayList<SetChallengeableEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SetChallengeableEventResponse typedResponse = new SetChallengeableEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.chainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.challengeable = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SetChallengeableEventResponse getSetChallengeableEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SETCHALLENGEABLE_EVENT, log);
        SetChallengeableEventResponse typedResponse = new SetChallengeableEventResponse();
        typedResponse.log = log;
        typedResponse.chainId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.challengeable = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<SetChallengeableEventResponse> setChallengeableEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSetChallengeableEventFromLog(log));
    }

    public Flowable<SetChallengeableEventResponse> setChallengeableEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SETCHALLENGEABLE_EVENT));
        return setChallengeableEventFlowable(filter);
    }

    public static List<UploadSuccessEventResponse> getUploadSuccessEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(UPLOADSUCCESS_EVENT, transactionReceipt);
        ArrayList<UploadSuccessEventResponse> responses = new ArrayList<UploadSuccessEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UploadSuccessEventResponse typedResponse = new UploadSuccessEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.epoch = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.height = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.txNums = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.root = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.signerSet = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static UploadSuccessEventResponse getUploadSuccessEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(UPLOADSUCCESS_EVENT, log);
        UploadSuccessEventResponse typedResponse = new UploadSuccessEventResponse();
        typedResponse.log = log;
        typedResponse.epoch = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.height = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.txNums = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.root = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.signerSet = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
        return typedResponse;
    }

    public Flowable<UploadSuccessEventResponse> uploadSuccessEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getUploadSuccessEventFromLog(log));
    }

    public Flowable<UploadSuccessEventResponse> uploadSuccessEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UPLOADSUCCESS_EVENT));
        return uploadSuccessEventFlowable(filter);
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

    public RemoteFunctionCall<TransactionReceipt> addChainConfig(BigInteger _chainId, byte[] _config) {
        final Function function = new Function(
                FUNC_ADDCHAINCONFIG, 
                Arrays.<Type>asList(new Uint16(_chainId),
                new DynamicBytes(_config)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addIvyBlock(AddBlkParams _addBlkParams) {
        final Function function = new Function(
                FUNC_ADDIVYBLOCK, 
                Arrays.<Type>asList(_addBlkParams), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> challengeable(BigInteger _chainId) {
        final Function function = new Function(FUNC_CHALLENGEABLE, 
                Arrays.<Type>asList(new Uint16(_chainId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> checkChainVerison(BigInteger _chainId, BigInteger _start, BigInteger _end) {
        final Function function = new Function(FUNC_CHECKCHAINVERISON, 
                Arrays.<Type>asList(new Uint16(_chainId),
                new Uint64(_start),
                new Uint64(_end)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, BigInteger, String>> getBlkFeeInfo(BigInteger _ivyHeight) {
        final Function function = new Function(FUNC_GETBLKFEEINFO, 
                Arrays.<Type>asList(new Uint256(_ivyHeight)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint64>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, BigInteger, String>>(function,
                new Callable<Tuple3<BigInteger, BigInteger, String>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<byte[]> getBlkRoot(BigInteger _ivyHeight) {
        final Function function = new Function(FUNC_GETBLKROOT, 
                Arrays.<Type>asList(new Uint256(_ivyHeight)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<BigInteger> getBlkSignerSet(BigInteger _ivyHeight) {
        final Function function = new Function(FUNC_GETBLKSIGNERSET, 
                Arrays.<Type>asList(new Uint256(_ivyHeight)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getBlkUploadTime(BigInteger _ivyHeight) {
        final Function function = new Function(FUNC_GETBLKUPLOADTIME, 
                Arrays.<Type>asList(new Uint256(_ivyHeight)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getChainLib(BigInteger _chainId, BigInteger _height) {
        final Function function = new Function(FUNC_GETCHAINLIB, 
                Arrays.<Type>asList(new Uint16(_chainId),
                new Uint64(_height)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<HeaderState> getDefaultHeader(BigInteger _chainId, BigInteger _height) {
        final Function function = new Function(FUNC_GETDEFAULTHEADER, 
                Arrays.<Type>asList(new Uint16(_chainId),
                new Uint64(_height)),
                Arrays.<TypeReference<?>>asList(new TypeReference<HeaderState>() {}));
        return executeRemoteCallSingleValueReturn(function, HeaderState.class);
    }

    public RemoteFunctionCall<BigInteger> getLatestValidHeight(BigInteger _epoch) {
        final Function function = new Function(FUNC_GETLATESTVALIDHEIGHT, 
                Arrays.<Type>asList(new Uint256(_epoch)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<HeaderState> getValidHeader(BigInteger _ivyHeight, byte[] _proof) {
        final Function function = new Function(FUNC_GETVALIDHEADER, 
                Arrays.<Type>asList(new Uint256(_ivyHeight),
                new DynamicBytes(_proof)),
                Arrays.<TypeReference<?>>asList(new TypeReference<HeaderState>() {}));
        return executeRemoteCallSingleValueReturn(function, HeaderState.class);
    }

    public RemoteFunctionCall<Boolean> isIvyBlockChallengeable(BigInteger _ivyHeight) {
        final Function function = new Function(FUNC_ISIVYBLOCKCHALLENGEABLE, 
                Arrays.<Type>asList(new Uint256(_ivyHeight)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeChain(BigInteger _chainId) {
        final Function function = new Function(
                FUNC_REMOVECHAIN, 
                Arrays.<Type>asList(new Uint16(_chainId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setChallengeable(BigInteger _chainId, Boolean _challengeable) {
        final Function function = new Function(
                FUNC_SETCHALLENGEABLE, 
                Arrays.<Type>asList(new Uint16(_chainId),
                new Bool(_challengeable)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> validateIvyBlock(BigInteger _ivyHeight, byte[] _combinedRoot, byte[] _data) {
        final Function function = new Function(FUNC_VALIDATEIVYBLOCK, 
                Arrays.<Type>asList(new Uint256(_ivyHeight),
                new Bytes32(_combinedRoot),
                new DynamicBytes(_data)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Packet> validateTxProofByHeader(BigInteger _chainId, byte[] _root, byte[] _proof) {
        final Function function = new Function(FUNC_VALIDATETXPROOFBYHEADER, 
                Arrays.<Type>asList(new Uint16(_chainId),
                new Bytes32(_root),
                new DynamicBytes(_proof)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Packet>() {}));
        return executeRemoteCallSingleValueReturn(function, Packet.class);
    }

    public RemoteFunctionCall<Packet> validateTxProofByIvy(BigInteger _chainId, BigInteger _ivyHeight, byte[] _proof) {
        final Function function = new Function(FUNC_VALIDATETXPROOFBYIVY, 
                Arrays.<Type>asList(new Uint16(_chainId),
                new Uint256(_ivyHeight),
                new DynamicBytes(_proof)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Packet>() {}));
        return executeRemoteCallSingleValueReturn(function, Packet.class);
    }

    @Deprecated
    public static IIvyChainManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyChainManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IIvyChainManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyChainManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IIvyChainManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IIvyChainManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IIvyChainManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IIvyChainManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IIvyChainManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyChainManager.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyChainManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyChainManager.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<IIvyChainManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyChainManager.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyChainManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyChainManager.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class AddBlkParams extends DynamicStruct {
        public BigInteger epoch;

        public BigInteger ivyHeight;

        public BigInteger txNums;

        public String uploader;

        public byte[] root;

        public BigInteger signerSet;

        public BigInteger startGas;

        public byte[] data;

        public AddBlkParams(BigInteger epoch, BigInteger ivyHeight, BigInteger txNums, String uploader, byte[] root, BigInteger signerSet, BigInteger startGas, byte[] data) {
            super(new Uint256(epoch),
                    new Uint256(ivyHeight),
                    new Uint64(txNums),
                    new Address(160, uploader),
                    new Bytes32(root),
                    new Uint256(signerSet),
                    new Uint256(startGas),
                    new DynamicBytes(data));
            this.epoch = epoch;
            this.ivyHeight = ivyHeight;
            this.txNums = txNums;
            this.uploader = uploader;
            this.root = root;
            this.signerSet = signerSet;
            this.startGas = startGas;
            this.data = data;
        }

        public AddBlkParams(Uint256 epoch, Uint256 ivyHeight, Uint64 txNums, Address uploader, Bytes32 root, Uint256 signerSet, Uint256 startGas, DynamicBytes data) {
            super(epoch, ivyHeight, txNums, uploader, root, signerSet, startGas, data);
            this.epoch = epoch.getValue();
            this.ivyHeight = ivyHeight.getValue();
            this.txNums = txNums.getValue();
            this.uploader = uploader.getValue();
            this.root = root.getValue();
            this.signerSet = signerSet.getValue();
            this.startGas = startGas.getValue();
            this.data = data.getValue();
        }
    }

    public static class HeaderState extends StaticStruct {
        public BigInteger chainId;

        public BigInteger height;

        public byte[] stateHash;

        public HeaderState(BigInteger chainId, BigInteger height, byte[] stateHash) {
            super(new Uint16(chainId),
                    new Uint64(height),
                    new Bytes32(stateHash));
            this.chainId = chainId;
            this.height = height;
            this.stateHash = stateHash;
        }

        public HeaderState(Uint16 chainId, Uint64 height, Bytes32 stateHash) {
            super(chainId, height, stateHash);
            this.chainId = chainId.getValue();
            this.height = height.getValue();
            this.stateHash = stateHash.getValue();
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

    public static class AddChainEventResponse extends BaseEventResponse {
        public BigInteger chainId;

        public BigInteger srcAddressSize;

        public BigInteger srcHeight;

        public String srcChainLib;

        public String srcValidationLib;

        public byte[] srcChainIvy;

        public byte[] srcGenesis;
    }

    public static class AddChainConfigEventResponse extends BaseEventResponse {
        public BigInteger chainId;

        public BigInteger srcHeight;

        public String srcChainLib;

        public byte[] srcGenesis;
    }

    public static class AddIvyBlockEventResponse extends BaseEventResponse {
        public BigInteger ivyHeight;

        public byte[] combinedRoot;

        public byte[] data;
    }

    public static class EmptyBlkEventResponse extends BaseEventResponse {
        public BigInteger epoch;

        public BigInteger height;

        public byte[] root;

        public byte[] data;
    }

    public static class RemoveChainEventResponse extends BaseEventResponse {
        public BigInteger chainId;
    }

    public static class SetChallengeableEventResponse extends BaseEventResponse {
        public BigInteger chainId;

        public Boolean challengeable;
    }

    public static class UploadSuccessEventResponse extends BaseEventResponse {
        public BigInteger epoch;

        public BigInteger height;

        public BigInteger txNums;

        public byte[] root;

        public byte[] data;

        public BigInteger signerSet;
    }
}
