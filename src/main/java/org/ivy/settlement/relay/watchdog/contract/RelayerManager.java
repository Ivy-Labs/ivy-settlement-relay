package org.ivy.settlement.relay.watchdog.contract;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
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
public class RelayerManager extends Contract {
    public static final String BINARY = "60c06040525f805460ff19166001179055348015601a575f80fd5b506040516121fe3803806121fe833981016040819052603791604c565b6001600160a01b0390911660805260a0526081565b5f8060408385031215605c575f80fd5b82516001600160a01b03811681146071575f80fd5b6020939093015192949293505050565b60805160a0516121406100be5f395f61031b01525f81816106400152818161092601528181610e5701528181610f0d01526112bb01526121405ff3fe60806040526004361061008f575f3560e01c806376a386dc1161005757806376a386dc1461010c578063884373b2146101a85780639924d33b146101bb578063aaff5f1614610223578063f4cf4c7214610242575f80fd5b806317a1d80f146100935780634502e57c146100b457806351cff8d9146100c75780635d306de3146100e6578063653defbe146100f9575b5f80fd5b34801561009e575f80fd5b506100b26100ad366004611770565b610255565b005b6100b26100c23660046117d6565b610319565b3480156100d2575f80fd5b506100b26100e1366004611770565b610480565b6100b26100f4366004611837565b61063d565b6100b2610107366004611897565b6108a9565b348015610117575f80fd5b50610176610126366004611997565b600360209081525f92835260409092208151808301840180519281529084019290930191909120915280546001909101546001600160401b03821691600160401b90046001600160a01b03169083565b604080516001600160401b0390941684526001600160a01b039092166020840152908201526060015b60405180910390f35b6100b26101b6366004611837565b610923565b3480156101c6575f80fd5b5061020b6101d5366004611997565b600260209081525f928352604090922081518083018401805192815290840192909301919091209152546001600160401b031681565b6040516001600160401b03909116815260200161019f565b34801561022e575f80fd5b506100b261023d366004611a23565b610bcc565b6100b2610250366004611a86565b610e39565b333b6102a85760405162461bcd60e51b815260206004820152601b60248201527f4170706c69636174696f6e204973204e6f7420436f6e7472616374000000000060448201526064015b60405180910390fd5b806040516020016102b99190611b05565b60408051601f198184030181528282528051602091820120335f90815260018352839020556001600160a01b038416835290517fe7b78c03fc46198ee94a6946701f7087391d46c62955316ba2fd3f376ba5c05f9281900390910190a150565b7f00000000000000000000000000000000000000000000000000000000000000003410156103805760405162461bcd60e51b8152602060048201526014602482015273125b9cdd59999a58da595b9d0811195c1bdcda5d60621b604482015260640161029f565b5f336040516020016103929190611b05565b60408051601f19818403018152828252805160209182012060608401835233845234848301528251601f87018390048302810183018452868152909450918301919086908690819084018382808284375f92018290525093909452505083815260046020908152604091829020845181546001600160a01b0319166001600160a01b0390911617815590840151600182015590830151909150600282019061043a9082611ba6565b509050507ff010872d82e330ab6c2da983678708ad4300a6bc1003cc87a5b08331ee8ebc5d813485856040516104739493929190611c88565b60405180910390a1505050565b5f336040516020016104929190611b05565b6040516020818303038152906040528051906020012090505f5b5f82815260056020526040902054811015610598575f8281526005602052604081208054839081106104e0576104e0611cb1565b5f9182526020808320601083040154868452600682526040808520600f90941660029081026101000a90920461ffff168086529390925292205490925060ff1614801561055157505f83815260066020908152604080832061ffff8516845290915290206002810154600190910154145b61058f5760405162461bcd60e51b815260206004820152600f60248201526e43616e6e6f7420576974686472617760881b604482015260640161029f565b506001016104ac565b505f8181526004602052604080822060010154905190916001600160a01b0385169183156108fc0291849190818181858888f193505050501580156105df573d5f803e3d5ffd5b505f8281526004602090815260408083206001019290925581518481526001600160a01b038616918101919091529081018290527fe7284ffe0c70ad2f3b0aa15cde1cfe95f736935651a138725b21fd168edc5d6a90606001610473565b5f7f00000000000000000000000000000000000000000000000000000000000000006001600160a01b0316635cf9d3e234878787876040518663ffffffff1660e01b81526004016106919493929190611cc5565b5f6040518083038185885af11580156106ac573d5f803e3d5ffd5b50505050506040513d5f823e601f3d908101601f191682016040526106d49190810190611d64565b9050336040516020016106e79190611b05565b60405160208183030381529060405280519060200120815f01511461071e5760405162461bcd60e51b815260040161029f90611e51565b60025f826020015161ffff1661ffff1681526020019081526020015f208160a0015160405161074d9190611e80565b90815260405190819003602001902080545f90610772906001600160401b0316611eaa565b91906101000a8154816001600160401b0302191690836001600160401b0316021790556001600160401b031681606001516001600160401b0316146107c95760405162461bcd60e51b815260040161029f90611ed4565b60808101516001600160a01b0316301461081d5760405162461bcd60e51b8152602060048201526015602482015274111cdd081059191c995cdcc8125b98dbdc9c9958dd605a1b604482015260640161029f565b5f8160e001518060200190518101906108369190611efd565b6001600160801b03169050610853825f01518360200151836110d2565b81516020808401516040805193845261ffff9091169183019190915281018290527fc58cc7ed896be0c60b4512a6568f26f376862b62cb600a2e160efb44e86287fa9060600160405180910390a1505050505050565b5f6108b686858585611175565b80515f90815260066020908152604080832061ffff8b16845290915281206001908101805493945090929091906108ee908490611f23565b9250508190555061091b815f015182602001518360a00151846080015185606001518a8760e00151611367565b505050505050565b5f7f00000000000000000000000000000000000000000000000000000000000000006001600160a01b0316635cf9d3e234878787876040518663ffffffff1660e01b81526004016109779493929190611cc5565b5f6040518083038185885af1158015610992573d5f803e3d5ffd5b50505050506040513d5f823e601f3d908101601f191682016040526109ba9190810190611d64565b9050336040516020016109cd9190611b05565b60405160208183030381529060405280519060200120815f015114610a045760405162461bcd60e51b815260040161029f90611e51565b80515f908152600460205260409020546001600160a01b03163314610a6b5760405162461bcd60e51b815260206004820152601860248201527f4473742052656c6179657220556e726567697374657265640000000000000000604482015260640161029f565b60025f826020015161ffff1661ffff1681526020019081526020015f208160a00151604051610a9a9190611e80565b90815260405190819003602001902080545f90610abf906001600160401b0316611eaa565b91906101000a8154816001600160401b0302191690836001600160401b0316021790556001600160401b031681606001516001600160401b031614610b165760405162461bcd60e51b815260040161029f90611ed4565b60808101516001600160a01b03163014610b6a5760405162461bcd60e51b8152602060048201526015602482015274111cdd081059191c995cdcc8125b98dbdc9c9958dd605a1b604482015260640161029f565b610b7b815f0151826020015161165e565b805160208201516040517f4bfd27fe180e226e6749d25d9b50f339d6b473feb5beea8cb9a67daebcf1f50c92610bbd9290825261ffff16602082015260400190565b60405180910390a15050505050565b5f5460ff16600114610c205760405162461bcd60e51b815260206004820152601a60248201527f4976793a206e6f2072656365697665207265656e7472616e6379000000000000604482015260640161029f565b5f805460ff1916600217815561ffff86168152600360205260408082209051610c4c9087908790611f3c565b9081526040519081900360200190206001810154909150610caf5760405162461bcd60e51b815260206004820152601a60248201527f53746f726564205061796c6f6164204e6f6e6578697374656e74000000000000604482015260640161029f565b80546001600160401b031682148015610ce2575080600101548383604051610cd8929190611f3c565b6040518091039020145b610d205760405162461bcd60e51b815260206004820152600f60248201526e125b9d985b1a590814185e5b1bd859608a1b604482015260640161029f565b80546001600160e01b0319811682555f6001830181905561ffff88168152600260205260408082209051600160401b9093046001600160a01b031692610d699089908990611f3c565b90815260405190819003602001812054637b4d737760e11b82526001600160401b031691506001600160a01b0383169063f69ae6ee90610db7908b908b908b9087908c908c90600401611f4b565b5f604051808303815f87803b158015610dce575f80fd5b505af1158015610de0573d5f803e3d5ffd5b505050507f612434f39581c8e7d99746c9c20c6eb0ce8c0eb99f007c5719d620841370957d8888888486604051610e1b959493929190611f97565b60405180910390a150505f805460ff19166001179055505050505050565b6040516384e4232760e01b81526001600160401b03851660048201527f00000000000000000000000000000000000000000000000000000000000000006001600160a01b0316906384e4232790602401602060405180830381865afa158015610ea4573d5f803e3d5ffd5b505050506040513d601f19601f82011682018060405250810190610ec89190611fdf565b610f0a5760405162461bcd60e51b8152602060048201526013602482015272125d9e48109b1ac8155b98dbdb5b5a5d1d1959606a1b604482015260640161029f565b5f7f00000000000000000000000000000000000000000000000000000000000000006001600160a01b0316635cf9d3e234898888886040518663ffffffff1660e01b8152600401610f5e9493929190611cc5565b5f6040518083038185885af1158015610f79573d5f803e3d5ffd5b50505050506040513d5f823e601f3d908101601f19168201604052610fa19190810190611d64565b9050610fc9815f015182602001518360a00151846080015185606001518b8760e00151611367565b80515f90815260066020908152604080832061ffff8b16845290915281206001908101805491929091610ffd908490611f23565b909155505080515f90815260046020526040812060010154606490611023906028611ffe565b61102d9190612015565b6040519091506001600160a01b0384169082156108fc029083905f818181858888f19350505050158015611063573d5f803e3d5ffd5b5081515f90815260046020908152604080832060010192909255835182519081526001600160a01b038616918101919091529081018290527f536b3577cbd3956ee3d6859709b7bac4b4910330b0b637d0da8c17c00a8dfbe79060600160405180910390a15050505050505050565b5f83815260066020908152604080832061ffff8616845290915290205460ff166001146111415760405162461bcd60e51b815260206004820152601860248201527f5372632052656c6179657220556e726567697374657265640000000000000000604482015260640161029f565b5f92835260066020908152604080852061ffff9490941685529290529120600280820192909255805460ff19169091179055565b60408051610100810182525f808252602080830182905282840182905260608084018390526080840183905260a0840181905260c0840183905260e08401529251919290916111c691339101611b05565b60408051601f1981840301815291815281516020928301205f8181526006845282812061ffff8b16825290935291205490915060ff166001148061125857505f81815260066020908152604080832061ffff8a16845290915290205460ff16600214801561125857505f81815260066020908152604080832061ffff8a16845290915290206001810154600290910154115b6112a45760405162461bcd60e51b815260206004820152601860248201527f536f757263652072656c6179657220556e616374697665640000000000000000604482015260640161029f565b604051632e7ce9f160e11b81526001600160a01b037f00000000000000000000000000000000000000000000000000000000000000001690635cf9d3e29034906112f8908a908a908a908a90600401611cc5565b5f6040518083038185885af1158015611313573d5f803e3d5ffd5b50505050506040513d5f823e601f3d908101601f1916820160405261133b9190810190611d64565b8051909250811461135e5760405162461bcd60e51b815260040161029f90611e51565b50949350505050565b6001600160a01b0384165f9081526001602052604090205487146113c45760405162461bcd60e51b81526020600482015260146024820152732ab730baba3437b934bd32b2102932b630bcb2b960611b604482015260640161029f565b61ffff86165f908152600260205260409081902090516113e5908790611e80565b90815260405190819003602001902080545f9061140a906001600160401b0316611eaa565b91906101000a8154816001600160401b0302191690836001600160401b0316021790556001600160401b0316836001600160401b03161461145d5760405162461bcd60e51b815260040161029f90611ed4565b61ffff86165f90815260036020526040808220905161147d908890611e80565b9081526040519081900360200190206001810154909150156114d45760405162461bcd60e51b815260206004820152601060248201526f4d65737361676520426c6f636b696e6760801b604482015260640161029f565b604051637b4d737760e11b81526001600160a01b0386169063f69ae6ee908590611508908b908b908a908990600401612062565b5f604051808303815f88803b15801561151f575f80fd5b5087f193505050508015611531575060015b611654573d80801561155e576040519150601f19603f3d011682016040523d82523d5f602084013e611563565b606091505b50604051806060016040528084516001600160401b03168152602001876001600160a01b03168152602001848051906020012081525060035f8a61ffff1661ffff1681526020019081526020015f20886040516115c09190611e80565b90815260408051918290036020908101832084518154928601516001600160a01b0316600160401b026001600160e01b03199093166001600160401b03909116179190911781559201516001909201919091557f0f9e4d95b62f08222d612b5ab92039cd8fbbbea550a95e8df9f927436bbdf5db9061164a908a908a908a908a90899088906120aa565b60405180910390a1505b5050505050505050565b5f82815260066020908152604080832061ffff8516845290915290205460ff16156116cb5760405162461bcd60e51b815260206004820152601e60248201527f5372632052656c6179657220416c726561647920526567697374657265640000604482015260640161029f565b6040805160608101825260018082525f60208084018281528486018381528884526006835286842061ffff9889168086529084528785209651875460ff191660ff90911617875591518686015551600295860155968252600581529381208054928301815581529290922060108304018054600f9093169091026101000a9283021990911691909202179055565b6001600160a01b038116811461176d575f80fd5b50565b5f60208284031215611780575f80fd5b813561178b81611759565b9392505050565b5f8083601f8401126117a2575f80fd5b5081356001600160401b038111156117b8575f80fd5b6020830191508360208285010111156117cf575f80fd5b9250929050565b5f80602083850312156117e7575f80fd5b82356001600160401b038111156117fc575f80fd5b61180885828601611792565b90969095509350505050565b61ffff8116811461176d575f80fd5b6001600160401b038116811461176d575f80fd5b5f805f806060858703121561184a575f80fd5b843561185581611814565b9350602085013561186581611823565b925060408501356001600160401b0381111561187f575f80fd5b61188b87828801611792565b95989497509550505050565b5f805f805f608086880312156118ab575f80fd5b85356118b681611814565b94506020860135935060408601356118cd81611823565b925060608601356001600160401b038111156118e7575f80fd5b6118f388828901611792565b969995985093965092949392505050565b634e487b7160e01b5f52604160045260245ffd5b60405161010081016001600160401b038111828210171561193b5761193b611904565b60405290565b604051601f8201601f191681016001600160401b038111828210171561196957611969611904565b604052919050565b5f6001600160401b0382111561198957611989611904565b50601f01601f191660200190565b5f80604083850312156119a8575f80fd5b82356119b381611814565b915060208301356001600160401b038111156119cd575f80fd5b8301601f810185136119dd575f80fd5b80356119f06119eb82611971565b611941565b818152866020838501011115611a04575f80fd5b816020840160208301375f602083830101528093505050509250929050565b5f805f805f60608688031215611a37575f80fd5b8535611a4281611814565b945060208601356001600160401b03811115611a5c575f80fd5b611a6888828901611792565b90955093505060408601356001600160401b038111156118e7575f80fd5b5f805f805f8060a08789031215611a9b575f80fd5b8635611aa681611814565b9550602087013594506040870135611abd81611823565b935060608701356001600160401b03811115611ad7575f80fd5b611ae389828a01611792565b9094509250506080870135611af781611759565b809150509295509295509295565b60609190911b6bffffffffffffffffffffffff1916815260140190565b600181811c90821680611b3657607f821691505b602082108103611b5457634e487b7160e01b5f52602260045260245ffd5b50919050565b601f821115611ba157805f5260205f20601f840160051c81016020851015611b7f5750805b601f840160051c820191505b81811015611b9e575f8155600101611b8b565b50505b505050565b81516001600160401b03811115611bbf57611bbf611904565b611bd381611bcd8454611b22565b84611b5a565b6020601f821160018114611c05575f8315611bee5750848201515b5f19600385901b1c1916600184901b178455611b9e565b5f84815260208120601f198516915b82811015611c345787850151825560209485019460019092019101611c14565b5084821015611c5157868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b81835281816020850137505f828201602090810191909152601f909101601f19169091010190565b848152836020820152606060408201525f611ca7606083018486611c60565b9695505050505050565b634e487b7160e01b5f52603260045260245ffd5b61ffff851681526001600160401b0384166020820152606060408201525f611ca7606083018486611c60565b8051611cfc81611814565b919050565b8051611cfc81611823565b8051611cfc81611759565b5f82601f830112611d26575f80fd5b8151611d346119eb82611971565b818152846020838601011115611d48575f80fd5b8160208501602083015e5f918101602001919091529392505050565b5f60208284031215611d74575f80fd5b81516001600160401b03811115611d89575f80fd5b82016101008185031215611d9b575f80fd5b611da3611918565b81518152611db360208301611cf1565b6020820152611dc460408301611cf1565b6040820152611dd560608301611d01565b6060820152611de660808301611d0c565b608082015260a08201516001600160401b03811115611e03575f80fd5b611e0f86828501611d17565b60a08301525060c0828101519082015260e08201516001600160401b03811115611e37575f80fd5b611e4386828501611d17565b60e083015250949350505050565b60208082526015908201527414995b185e595c88125908135a5cdb585d18da1959605a1b604082015260600190565b5f82518060208501845e5f920191825250919050565b634e487b7160e01b5f52601160045260245ffd5b5f6001600160401b0382166001600160401b038103611ecb57611ecb611e96565b60010192915050565b6020808252600f908201526e139bdb98d948125b98dbdc9c9958dd608a1b604082015260600190565b5f60208284031215611f0d575f80fd5b81516001600160801b038116811461178b575f80fd5b80820180821115611f3657611f36611e96565b92915050565b818382375f9101908152919050565b61ffff87168152608060208201525f611f68608083018789611c60565b6001600160401b03861660408401528281036060840152611f8a818587611c60565b9998505050505050505050565b61ffff86168152608060208201525f611fb4608083018688611c60565b6001600160401b03949094166040830152506001600160a01b03919091166060909101529392505050565b5f60208284031215611fef575f80fd5b8151801515811461178b575f80fd5b8082028115828204841417611f3657611f36611e96565b5f8261202f57634e487b7160e01b5f52601260045260245ffd5b500490565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b61ffff85168152608060208201525f61207e6080830186612034565b6001600160401b0385166040840152828103606084015261209f8185612034565b979650505050505050565b61ffff8716815260c060208201525f6120c660c0830188612034565b6001600160a01b03871660408401526001600160401b038616606084015282810360808401526120f68186612034565b905082810360a0840152611f8a818561203456fea2646970667358221220bef46201cd095d8d0b90d718ff07812ffef86ae146fe82ee8a3c22ea56c1725164736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACTIVATESRCRELAYER = "activateSrcRelayer";

    public static final String FUNC_INBOUNDNONCE = "inboundNonce";

    public static final String FUNC_PENALITYRELAYER = "penalityRelayer";

    public static final String FUNC_REGISTERAPP = "registerApp";

    public static final String FUNC_REGISTERDSTRELAYER = "registerDstRelayer";

    public static final String FUNC_RETRYPAYLOAD = "retryPayload";

    public static final String FUNC_STOREDPAYLOAD = "storedPayload";

    public static final String FUNC_SUBMITTX = "submitTx";

    public static final String FUNC_UNACTIVATESRCRELAYER = "unactivateSrcRelayer";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final Event ADDAPPLICATION_EVENT = new Event("AddApplication", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event DSTRELAYERREGISTERED_EVENT = new Event("DstRelayerRegistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event PAYLOADCLEARED_EVENT = new Event("PayloadCleared", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Uint64>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event PAYLOADSTORED_EVENT = new Event("PayloadStored", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Address>() {}, new TypeReference<Uint64>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}));
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
    protected RelayerManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RelayerManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RelayerManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RelayerManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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
                new Uint64(_ivyHeight),
                new DynamicBytes(_proof)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> inboundNonce(BigInteger param0, byte[] param1) {
        final Function function = new Function(FUNC_INBOUNDNONCE, 
                Arrays.<Type>asList(new Uint16(param0),
                new DynamicBytes(param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> penalityRelayer(BigInteger _srcChainId, BigInteger _gasLimit, BigInteger _ivyHeight, byte[] _proof, String _reciever, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PENALITYRELAYER, 
                Arrays.<Type>asList(new Uint16(_srcChainId),
                new Uint256(_gasLimit),
                new Uint64(_ivyHeight),
                new DynamicBytes(_proof),
                new Address(160, _reciever)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> registerApp(String _relayer) {
        final Function function = new Function(
                FUNC_REGISTERAPP, 
                Arrays.<Type>asList(new Address(160, _relayer)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> registerDstRelayer(String _description, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_REGISTERDSTRELAYER, 
                Arrays.<Type>asList(new Utf8String(_description)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
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

    public RemoteFunctionCall<Tuple3<BigInteger, String, byte[]>> storedPayload(BigInteger param0, byte[] param1) {
        final Function function = new Function(FUNC_STOREDPAYLOAD, 
                Arrays.<Type>asList(new Uint16(param0),
                new DynamicBytes(param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, String, byte[]>>(function,
                new Callable<Tuple3<BigInteger, String, byte[]>>() {
                    @Override
                    public Tuple3<BigInteger, String, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, String, byte[]>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (byte[]) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> submitTx(BigInteger _srcChainId, BigInteger _gasLimit, BigInteger _ivyHeight, byte[] _proof, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SUBMITTX, 
                Arrays.<Type>asList(new Uint16(_srcChainId),
                new Uint256(_gasLimit),
                new Uint64(_ivyHeight),
                new DynamicBytes(_proof)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> unactivateSrcRelayer(BigInteger _srcChainId, BigInteger _ivyHeight, byte[] _proof, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_UNACTIVATESRCRELAYER, 
                Arrays.<Type>asList(new Uint16(_srcChainId),
                new Uint64(_ivyHeight),
                new DynamicBytes(_proof)),
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
    public static RelayerManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RelayerManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RelayerManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RelayerManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RelayerManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new RelayerManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RelayerManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RelayerManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RelayerManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _networkManager, BigInteger _minStakeAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, _networkManager),
                new Uint256(_minStakeAmount)));
        return deployRemoteCall(RelayerManager.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<RelayerManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _networkManager, BigInteger _minStakeAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, _networkManager),
                new Uint256(_minStakeAmount)));
        return deployRemoteCall(RelayerManager.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<RelayerManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _networkManager, BigInteger _minStakeAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, _networkManager),
                new Uint256(_minStakeAmount)));
        return deployRemoteCall(RelayerManager.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<RelayerManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _networkManager, BigInteger _minStakeAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, _networkManager),
                new Uint256(_minStakeAmount)));
        return deployRemoteCall(RelayerManager.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class DstRelayerRegisteredEventResponse extends BaseEventResponse {
        public byte[] relayerId;

        public BigInteger deposit;

        public String description;
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
