package org.ivy.settlement.relay.watchdog.contract;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.*;
import org.web3j.abi.datatypes.reflection.Parameterized;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
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
public class IIvyChain extends Contract {
    public static final String BINARY = "";

    private static String librariesLinkedBinary;

    public static final String FUNC_INITROUND = "initRound";

    public static final String FUNC_NEXTCHALLENGESTEP = "nextChallengeStep";

    public static final String FUNC_UPDATECHAINCONFIG = "updateChainConfig";

    public static final String FUNC_VALIDATEPUBLISH = "validatePublish";

    @Deprecated
    protected IIvyChain(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IIvyChain(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IIvyChain(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IIvyChain(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Round> initRound() {
        final Function function = new Function(FUNC_INITROUND, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Round>() {}));
        return executeRemoteCallSingleValueReturn(function, Round.class);
    }

    public RemoteFunctionCall<Game> nextChallengeStep(BigInteger disputeIndex, ChallState state, List<byte[]> datas) {
        final Function function = new Function(FUNC_NEXTCHALLENGESTEP, 
                Arrays.<Type>asList(new Uint32(disputeIndex),
                state, 
                new DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                        org.web3j.abi.datatypes.DynamicBytes.class,
                        org.web3j.abi.Utils.typeMap(datas, org.web3j.abi.datatypes.DynamicBytes.class))), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Game>() {}));
        return executeRemoteCallSingleValueReturn(function, Game.class);
    }

    public RemoteFunctionCall<TransactionReceipt> updateChainConfig(byte[] _config) {
        final Function function = new Function(
                FUNC_UPDATECHAINCONFIG, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(_config)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Game> validatePublish(BigInteger step, ChallState state, List<byte[]> publish, byte[] proof) {
        final Function function = new Function(FUNC_VALIDATEPUBLISH, 
                Arrays.<Type>asList(new Uint32(step),
                state, 
                new DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                        org.web3j.abi.datatypes.DynamicBytes.class,
                        org.web3j.abi.Utils.typeMap(publish, org.web3j.abi.datatypes.DynamicBytes.class)), 
                new org.web3j.abi.datatypes.DynamicBytes(proof)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Game>() {}));
        return executeRemoteCallSingleValueReturn(function, Game.class);
    }

    @Deprecated
    public static IIvyChain load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyChain(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IIvyChain load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IIvyChain(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IIvyChain load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IIvyChain(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IIvyChain load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IIvyChain(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IIvyChain> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyChain.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyChain> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyChain.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<IIvyChain> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IIvyChain.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IIvyChain> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IIvyChain.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class Round extends StaticStruct {
        public BigInteger step;

        public BigInteger timeLeft;

        public Round(BigInteger step, BigInteger timeLeft) {
            super(new Uint32(step),
                    new Uint256(timeLeft));
            this.step = step;
            this.timeLeft = timeLeft;
        }

        public Round(Uint32 step, Uint256 timeLeft) {
            super(step, timeLeft);
            this.step = step.getValue();
            this.timeLeft = timeLeft.getValue();
        }
    }

    public static class ChallState extends DynamicStruct {
        public BigInteger chainId;

        public BigInteger seq;

        public BigInteger start;

        public BigInteger end;

        public List<byte[]> segments;

        public byte[] extra;

        public ChallState(BigInteger chainId, BigInteger seq, BigInteger start, BigInteger end, List<byte[]> segments, byte[] extra) {
            super(new Uint16(chainId),
                    new Uint64(seq),
                    new Uint64(start),
                    new Uint64(end),
                    new DynamicArray<Bytes32>(
                            Bytes32.class,
                            org.web3j.abi.Utils.typeMap(segments, Bytes32.class)),
                    new Bytes32(extra));
            this.chainId = chainId;
            this.seq = seq;
            this.start = start;
            this.end = end;
            this.segments = segments;
            this.extra = extra;
        }

        public ChallState(Uint16 chainId, Uint64 seq, Uint64 start, Uint64 end, @Parameterized(type = Bytes32.class) DynamicArray<Bytes32> segments, Bytes32 extra) {
            super(chainId, seq, start, end, segments, extra);
            this.chainId = chainId.getValue();
            this.seq = seq.getValue();
            this.start = start.getValue();
            this.end = end.getValue();
            this.segments = segments.getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
            this.extra = extra.getValue();
        }
    }

    public static class Game extends DynamicStruct {
        public Round round;

        public ChallState state;

        public Game(Round round, ChallState state) {
            super(round, 
                    state);
            this.round = round;
            this.state = state;
        }
    }
}
