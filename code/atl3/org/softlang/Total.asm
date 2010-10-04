<?xml version = '1.0' encoding = 'ISO-8859-1' ?>
<asm name="0">
	<cp>
		<constant value="Total"/>
		<constant value="links"/>
		<constant value="NTransientLinkSet;"/>
		<constant value="col"/>
		<constant value="J;"/>
		<constant value="main"/>
		<constant value="A"/>
		<constant value="0"/>
		<constant value="OclParametrizedType"/>
		<constant value="#native"/>
		<constant value="Collection"/>
		<constant value="J.setName(S):V"/>
		<constant value="OclSimpleType"/>
		<constant value="OclAny"/>
		<constant value="J.setElementType(J):V"/>
		<constant value="TransientLinkSet"/>
		<constant value="A.__matcher__():V"/>
		<constant value="A.__exec__():V"/>
		<constant value="self"/>
		<constant value="__matcher__"/>
		<constant value="A.__matchCompany2Total():V"/>
		<constant value="__matchCompany2Total"/>
		<constant value="Company"/>
		<constant value="Sequence"/>
		<constant value="IN"/>
		<constant value="MMOF!Classifier;.allInstancesFrom(S):QJ"/>
		<constant value="CJ.union(CJ):CJ"/>
		<constant value="1"/>
		<constant value="B.not():B"/>
		<constant value="37"/>
		<constant value="TransientLink"/>
		<constant value="Company2Total"/>
		<constant value="NTransientLink;.setRule(MATL!Rule;):V"/>
		<constant value="company"/>
		<constant value="NTransientLink;.addSourceElement(SJ):V"/>
		<constant value="t"/>
		<constant value="TotalWrapper"/>
		<constant value="NTransientLink;.addTargetElement(SJ):V"/>
		<constant value="NTransientLinkSet;.addLink(NTransientLink;):V"/>
		<constant value="10:7-10:25"/>
		<constant value="__resolve__"/>
		<constant value="J"/>
		<constant value="J.oclIsKindOf(J):B"/>
		<constant value="18"/>
		<constant value="NTransientLinkSet;.getLinkBySourceElement(S):QNTransientLink;"/>
		<constant value="J.oclIsUndefined():J"/>
		<constant value="15"/>
		<constant value="NTransientLink;.getTargetFromSource(J):J"/>
		<constant value="17"/>
		<constant value="30"/>
		<constant value="2"/>
		<constant value="A.__resolve__(J):J"/>
		<constant value="QJ.including(J):QJ"/>
		<constant value="QJ.flatten():QJ"/>
		<constant value="e"/>
		<constant value="value"/>
		<constant value="resolveTemp"/>
		<constant value="S"/>
		<constant value="NTransientLink;.getNamedTargetFromSource(JS):J"/>
		<constant value="name"/>
		<constant value="__exec__"/>
		<constant value="NTransientLinkSet;.getLinksByRule(S):QNTransientLink;"/>
		<constant value="A.__applyCompany2Total(NTransientLink;):V"/>
		<constant value="__applyCompany2Total"/>
		<constant value="NTransientLink;"/>
		<constant value="NTransientLink;.getSourceElement(S):J"/>
		<constant value="NTransientLink;.getTargetElement(S):J"/>
		<constant value="3"/>
		<constant value="Employee"/>
		<constant value="J.allInstances():J"/>
		<constant value="4"/>
		<constant value="salary"/>
		<constant value="CJ.including(J):CJ"/>
		<constant value="J.sum():J"/>
		<constant value="total"/>
		<constant value="11:15-11:31"/>
		<constant value="11:15-11:46"/>
		<constant value="11:62-11:63"/>
		<constant value="11:62-11:70"/>
		<constant value="11:15-11:71"/>
		<constant value="11:15-11:80"/>
		<constant value="11:6-11:80"/>
		<constant value="link"/>
	</cp>
	<field name="1" type="2"/>
	<field name="3" type="4"/>
	<operation name="5">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<load arg="7"/>
			<push arg="8"/>
			<push arg="9"/>
			<new/>
			<dup/>
			<push arg="10"/>
			<call arg="11"/>
			<dup/>
			<push arg="12"/>
			<push arg="9"/>
			<new/>
			<dup/>
			<push arg="13"/>
			<call arg="11"/>
			<call arg="14"/>
			<set arg="3"/>
			<load arg="7"/>
			<push arg="15"/>
			<push arg="9"/>
			<new/>
			<set arg="1"/>
			<load arg="7"/>
			<call arg="16"/>
			<load arg="7"/>
			<call arg="17"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="18" begin="0" end="24"/>
		</localvariabletable>
	</operation>
	<operation name="19">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<load arg="7"/>
			<call arg="20"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="18" begin="0" end="1"/>
		</localvariabletable>
	</operation>
	<operation name="21">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="22"/>
			<push arg="22"/>
			<findme/>
			<push arg="23"/>
			<push arg="9"/>
			<new/>
			<swap/>
			<dup_x1/>
			<push arg="24"/>
			<call arg="25"/>
			<call arg="26"/>
			<swap/>
			<pop/>
			<iterate/>
			<store arg="27"/>
			<pusht/>
			<call arg="28"/>
			<if arg="29"/>
			<load arg="7"/>
			<get arg="1"/>
			<push arg="30"/>
			<push arg="9"/>
			<new/>
			<dup/>
			<push arg="31"/>
			<call arg="32"/>
			<dup/>
			<push arg="33"/>
			<load arg="27"/>
			<call arg="34"/>
			<dup/>
			<push arg="35"/>
			<push arg="36"/>
			<push arg="0"/>
			<new/>
			<call arg="37"/>
			<call arg="38"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="39" begin="32" end="34"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="33" begin="14" end="36"/>
			<lve slot="0" name="18" begin="0" end="37"/>
		</localvariabletable>
	</operation>
	<operation name="40">
		<context type="6"/>
		<parameters>
			<parameter name="27" type="41"/>
		</parameters>
		<code>
			<load arg="27"/>
			<load arg="7"/>
			<get arg="3"/>
			<call arg="42"/>
			<if arg="43"/>
			<load arg="7"/>
			<get arg="1"/>
			<load arg="27"/>
			<call arg="44"/>
			<dup/>
			<call arg="45"/>
			<if arg="46"/>
			<load arg="27"/>
			<call arg="47"/>
			<goto arg="48"/>
			<pop/>
			<load arg="27"/>
			<goto arg="49"/>
			<push arg="23"/>
			<push arg="9"/>
			<new/>
			<load arg="27"/>
			<iterate/>
			<store arg="50"/>
			<load arg="7"/>
			<load arg="50"/>
			<call arg="51"/>
			<call arg="52"/>
			<enditerate/>
			<call arg="53"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="54" begin="23" end="27"/>
			<lve slot="0" name="18" begin="0" end="29"/>
			<lve slot="1" name="55" begin="0" end="29"/>
		</localvariabletable>
	</operation>
	<operation name="56">
		<context type="6"/>
		<parameters>
			<parameter name="27" type="41"/>
			<parameter name="50" type="57"/>
		</parameters>
		<code>
			<load arg="7"/>
			<get arg="1"/>
			<load arg="27"/>
			<call arg="44"/>
			<load arg="27"/>
			<load arg="50"/>
			<call arg="58"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="18" begin="0" end="6"/>
			<lve slot="1" name="55" begin="0" end="6"/>
			<lve slot="2" name="59" begin="0" end="6"/>
		</localvariabletable>
	</operation>
	<operation name="60">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<load arg="7"/>
			<get arg="1"/>
			<push arg="31"/>
			<call arg="61"/>
			<iterate/>
			<store arg="27"/>
			<load arg="7"/>
			<load arg="27"/>
			<call arg="62"/>
			<enditerate/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="54" begin="5" end="8"/>
			<lve slot="0" name="18" begin="0" end="9"/>
		</localvariabletable>
	</operation>
	<operation name="63">
		<context type="6"/>
		<parameters>
			<parameter name="27" type="64"/>
		</parameters>
		<code>
			<load arg="27"/>
			<push arg="33"/>
			<call arg="65"/>
			<store arg="50"/>
			<load arg="27"/>
			<push arg="35"/>
			<call arg="66"/>
			<store arg="67"/>
			<load arg="67"/>
			<dup/>
			<load arg="7"/>
			<push arg="23"/>
			<push arg="9"/>
			<new/>
			<push arg="68"/>
			<push arg="22"/>
			<findme/>
			<call arg="69"/>
			<iterate/>
			<store arg="70"/>
			<load arg="70"/>
			<get arg="71"/>
			<call arg="72"/>
			<enditerate/>
			<call arg="73"/>
			<call arg="51"/>
			<set arg="74"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="75" begin="14" end="16"/>
			<lne id="76" begin="14" end="17"/>
			<lne id="77" begin="20" end="20"/>
			<lne id="78" begin="20" end="21"/>
			<lne id="79" begin="11" end="23"/>
			<lne id="80" begin="11" end="24"/>
			<lne id="81" begin="9" end="26"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="4" name="54" begin="19" end="22"/>
			<lve slot="2" name="33" begin="3" end="27"/>
			<lve slot="3" name="35" begin="7" end="27"/>
			<lve slot="0" name="18" begin="0" end="27"/>
			<lve slot="1" name="82" begin="0" end="27"/>
		</localvariabletable>
	</operation>
</asm>
