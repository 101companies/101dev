<?xml version = '1.0' encoding = 'ISO-8859-1' ?>
<asm name="0">
	<cp>
		<constant value="Cut"/>
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
		<constant value="A.__matchCompany2Company():V"/>
		<constant value="A.__matchDept2Dept():V"/>
		<constant value="A.__matchEmployee2Employee():V"/>
		<constant value="A.__matchPerson2Person():V"/>
		<constant value="__matchCompany2Company"/>
		<constant value="Company"/>
		<constant value="Sequence"/>
		<constant value="IN"/>
		<constant value="MMOF!Classifier;.allInstancesFrom(S):QJ"/>
		<constant value="CJ.union(CJ):CJ"/>
		<constant value="1"/>
		<constant value="B.not():B"/>
		<constant value="37"/>
		<constant value="TransientLink"/>
		<constant value="Company2Company"/>
		<constant value="NTransientLink;.setRule(MATL!Rule;):V"/>
		<constant value="s"/>
		<constant value="NTransientLink;.addSourceElement(SJ):V"/>
		<constant value="t"/>
		<constant value="NTransientLink;.addTargetElement(SJ):V"/>
		<constant value="NTransientLinkSet;.addLink(NTransientLink;):V"/>
		<constant value="11:7-11:22"/>
		<constant value="__matchDept2Dept"/>
		<constant value="Dept"/>
		<constant value="Dept2Dept"/>
		<constant value="21:7-21:19"/>
		<constant value="__matchEmployee2Employee"/>
		<constant value="Employee"/>
		<constant value="Employee2Employee"/>
		<constant value="34:7-34:23"/>
		<constant value="__matchPerson2Person"/>
		<constant value="Person"/>
		<constant value="Person2Person"/>
		<constant value="45:7-45:21"/>
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
		<constant value="A.__applyCompany2Company(NTransientLink;):V"/>
		<constant value="A.__applyDept2Dept(NTransientLink;):V"/>
		<constant value="A.__applyEmployee2Employee(NTransientLink;):V"/>
		<constant value="A.__applyPerson2Person(NTransientLink;):V"/>
		<constant value="__applyCompany2Company"/>
		<constant value="NTransientLink;"/>
		<constant value="NTransientLink;.getSourceElement(S):J"/>
		<constant value="NTransientLink;.getTargetElement(S):J"/>
		<constant value="3"/>
		<constant value="depts"/>
		<constant value="12:14-12:15"/>
		<constant value="12:14-12:21"/>
		<constant value="12:5-12:21"/>
		<constant value="link"/>
		<constant value="__applyDept2Dept"/>
		<constant value="manager"/>
		<constant value="subunits"/>
		<constant value="22:13-22:14"/>
		<constant value="22:13-22:19"/>
		<constant value="22:5-22:19"/>
		<constant value="23:16-23:17"/>
		<constant value="23:16-23:25"/>
		<constant value="23:5-23:25"/>
		<constant value="24:17-24:18"/>
		<constant value="24:17-24:27"/>
		<constant value="24:5-24:27"/>
		<constant value="__applyEmployee2Employee"/>
		<constant value="person"/>
		<constant value="salary"/>
		<constant value="J./(J):J"/>
		<constant value="35:15-35:16"/>
		<constant value="35:15-35:23"/>
		<constant value="35:5-35:23"/>
		<constant value="36:15-36:16"/>
		<constant value="36:15-36:23"/>
		<constant value="36:26-36:27"/>
		<constant value="36:15-36:27"/>
		<constant value="36:5-36:27"/>
		<constant value="__applyPerson2Person"/>
		<constant value="address"/>
		<constant value="46:13-46:14"/>
		<constant value="46:13-46:19"/>
		<constant value="46:5-46:19"/>
		<constant value="47:16-47:17"/>
		<constant value="47:16-47:25"/>
		<constant value="47:5-47:25"/>
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
			<load arg="7"/>
			<call arg="21"/>
			<load arg="7"/>
			<call arg="22"/>
			<load arg="7"/>
			<call arg="23"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="18" begin="0" end="7"/>
		</localvariabletable>
	</operation>
	<operation name="24">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="25"/>
			<push arg="25"/>
			<findme/>
			<push arg="26"/>
			<push arg="9"/>
			<new/>
			<swap/>
			<dup_x1/>
			<push arg="27"/>
			<call arg="28"/>
			<call arg="29"/>
			<swap/>
			<pop/>
			<iterate/>
			<store arg="30"/>
			<pusht/>
			<call arg="31"/>
			<if arg="32"/>
			<load arg="7"/>
			<get arg="1"/>
			<push arg="33"/>
			<push arg="9"/>
			<new/>
			<dup/>
			<push arg="34"/>
			<call arg="35"/>
			<dup/>
			<push arg="36"/>
			<load arg="30"/>
			<call arg="37"/>
			<dup/>
			<push arg="38"/>
			<push arg="25"/>
			<push arg="25"/>
			<new/>
			<call arg="39"/>
			<call arg="40"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="41" begin="32" end="34"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="36" begin="14" end="36"/>
			<lve slot="0" name="18" begin="0" end="37"/>
		</localvariabletable>
	</operation>
	<operation name="42">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="43"/>
			<push arg="25"/>
			<findme/>
			<push arg="26"/>
			<push arg="9"/>
			<new/>
			<swap/>
			<dup_x1/>
			<push arg="27"/>
			<call arg="28"/>
			<call arg="29"/>
			<swap/>
			<pop/>
			<iterate/>
			<store arg="30"/>
			<pusht/>
			<call arg="31"/>
			<if arg="32"/>
			<load arg="7"/>
			<get arg="1"/>
			<push arg="33"/>
			<push arg="9"/>
			<new/>
			<dup/>
			<push arg="44"/>
			<call arg="35"/>
			<dup/>
			<push arg="36"/>
			<load arg="30"/>
			<call arg="37"/>
			<dup/>
			<push arg="38"/>
			<push arg="43"/>
			<push arg="25"/>
			<new/>
			<call arg="39"/>
			<call arg="40"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="45" begin="32" end="34"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="36" begin="14" end="36"/>
			<lve slot="0" name="18" begin="0" end="37"/>
		</localvariabletable>
	</operation>
	<operation name="46">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="47"/>
			<push arg="25"/>
			<findme/>
			<push arg="26"/>
			<push arg="9"/>
			<new/>
			<swap/>
			<dup_x1/>
			<push arg="27"/>
			<call arg="28"/>
			<call arg="29"/>
			<swap/>
			<pop/>
			<iterate/>
			<store arg="30"/>
			<pusht/>
			<call arg="31"/>
			<if arg="32"/>
			<load arg="7"/>
			<get arg="1"/>
			<push arg="33"/>
			<push arg="9"/>
			<new/>
			<dup/>
			<push arg="48"/>
			<call arg="35"/>
			<dup/>
			<push arg="36"/>
			<load arg="30"/>
			<call arg="37"/>
			<dup/>
			<push arg="38"/>
			<push arg="47"/>
			<push arg="25"/>
			<new/>
			<call arg="39"/>
			<call arg="40"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="49" begin="32" end="34"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="36" begin="14" end="36"/>
			<lve slot="0" name="18" begin="0" end="37"/>
		</localvariabletable>
	</operation>
	<operation name="50">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="51"/>
			<push arg="25"/>
			<findme/>
			<push arg="26"/>
			<push arg="9"/>
			<new/>
			<swap/>
			<dup_x1/>
			<push arg="27"/>
			<call arg="28"/>
			<call arg="29"/>
			<swap/>
			<pop/>
			<iterate/>
			<store arg="30"/>
			<pusht/>
			<call arg="31"/>
			<if arg="32"/>
			<load arg="7"/>
			<get arg="1"/>
			<push arg="33"/>
			<push arg="9"/>
			<new/>
			<dup/>
			<push arg="52"/>
			<call arg="35"/>
			<dup/>
			<push arg="36"/>
			<load arg="30"/>
			<call arg="37"/>
			<dup/>
			<push arg="38"/>
			<push arg="51"/>
			<push arg="25"/>
			<new/>
			<call arg="39"/>
			<call arg="40"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="53" begin="32" end="34"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="36" begin="14" end="36"/>
			<lve slot="0" name="18" begin="0" end="37"/>
		</localvariabletable>
	</operation>
	<operation name="54">
		<context type="6"/>
		<parameters>
			<parameter name="30" type="55"/>
		</parameters>
		<code>
			<load arg="30"/>
			<load arg="7"/>
			<get arg="3"/>
			<call arg="56"/>
			<if arg="57"/>
			<load arg="7"/>
			<get arg="1"/>
			<load arg="30"/>
			<call arg="58"/>
			<dup/>
			<call arg="59"/>
			<if arg="60"/>
			<load arg="30"/>
			<call arg="61"/>
			<goto arg="62"/>
			<pop/>
			<load arg="30"/>
			<goto arg="63"/>
			<push arg="26"/>
			<push arg="9"/>
			<new/>
			<load arg="30"/>
			<iterate/>
			<store arg="64"/>
			<load arg="7"/>
			<load arg="64"/>
			<call arg="65"/>
			<call arg="66"/>
			<enditerate/>
			<call arg="67"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="68" begin="23" end="27"/>
			<lve slot="0" name="18" begin="0" end="29"/>
			<lve slot="1" name="69" begin="0" end="29"/>
		</localvariabletable>
	</operation>
	<operation name="70">
		<context type="6"/>
		<parameters>
			<parameter name="30" type="55"/>
			<parameter name="64" type="71"/>
		</parameters>
		<code>
			<load arg="7"/>
			<get arg="1"/>
			<load arg="30"/>
			<call arg="58"/>
			<load arg="30"/>
			<load arg="64"/>
			<call arg="72"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="18" begin="0" end="6"/>
			<lve slot="1" name="69" begin="0" end="6"/>
			<lve slot="2" name="73" begin="0" end="6"/>
		</localvariabletable>
	</operation>
	<operation name="74">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<load arg="7"/>
			<get arg="1"/>
			<push arg="34"/>
			<call arg="75"/>
			<iterate/>
			<store arg="30"/>
			<load arg="7"/>
			<load arg="30"/>
			<call arg="76"/>
			<enditerate/>
			<load arg="7"/>
			<get arg="1"/>
			<push arg="44"/>
			<call arg="75"/>
			<iterate/>
			<store arg="30"/>
			<load arg="7"/>
			<load arg="30"/>
			<call arg="77"/>
			<enditerate/>
			<load arg="7"/>
			<get arg="1"/>
			<push arg="48"/>
			<call arg="75"/>
			<iterate/>
			<store arg="30"/>
			<load arg="7"/>
			<load arg="30"/>
			<call arg="78"/>
			<enditerate/>
			<load arg="7"/>
			<get arg="1"/>
			<push arg="52"/>
			<call arg="75"/>
			<iterate/>
			<store arg="30"/>
			<load arg="7"/>
			<load arg="30"/>
			<call arg="79"/>
			<enditerate/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="68" begin="5" end="8"/>
			<lve slot="1" name="68" begin="15" end="18"/>
			<lve slot="1" name="68" begin="25" end="28"/>
			<lve slot="1" name="68" begin="35" end="38"/>
			<lve slot="0" name="18" begin="0" end="39"/>
		</localvariabletable>
	</operation>
	<operation name="80">
		<context type="6"/>
		<parameters>
			<parameter name="30" type="81"/>
		</parameters>
		<code>
			<load arg="30"/>
			<push arg="36"/>
			<call arg="82"/>
			<store arg="64"/>
			<load arg="30"/>
			<push arg="38"/>
			<call arg="83"/>
			<store arg="84"/>
			<load arg="84"/>
			<dup/>
			<load arg="7"/>
			<load arg="64"/>
			<get arg="85"/>
			<call arg="65"/>
			<set arg="85"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="86" begin="11" end="11"/>
			<lne id="87" begin="11" end="12"/>
			<lne id="88" begin="9" end="14"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="36" begin="3" end="15"/>
			<lve slot="3" name="38" begin="7" end="15"/>
			<lve slot="0" name="18" begin="0" end="15"/>
			<lve slot="1" name="89" begin="0" end="15"/>
		</localvariabletable>
	</operation>
	<operation name="90">
		<context type="6"/>
		<parameters>
			<parameter name="30" type="81"/>
		</parameters>
		<code>
			<load arg="30"/>
			<push arg="36"/>
			<call arg="82"/>
			<store arg="64"/>
			<load arg="30"/>
			<push arg="38"/>
			<call arg="83"/>
			<store arg="84"/>
			<load arg="84"/>
			<dup/>
			<load arg="7"/>
			<load arg="64"/>
			<get arg="73"/>
			<call arg="65"/>
			<set arg="73"/>
			<dup/>
			<load arg="7"/>
			<load arg="64"/>
			<get arg="91"/>
			<call arg="65"/>
			<set arg="91"/>
			<dup/>
			<load arg="7"/>
			<load arg="64"/>
			<get arg="92"/>
			<call arg="65"/>
			<set arg="92"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="93" begin="11" end="11"/>
			<lne id="94" begin="11" end="12"/>
			<lne id="95" begin="9" end="14"/>
			<lne id="96" begin="17" end="17"/>
			<lne id="97" begin="17" end="18"/>
			<lne id="98" begin="15" end="20"/>
			<lne id="99" begin="23" end="23"/>
			<lne id="100" begin="23" end="24"/>
			<lne id="101" begin="21" end="26"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="36" begin="3" end="27"/>
			<lve slot="3" name="38" begin="7" end="27"/>
			<lve slot="0" name="18" begin="0" end="27"/>
			<lve slot="1" name="89" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="102">
		<context type="6"/>
		<parameters>
			<parameter name="30" type="81"/>
		</parameters>
		<code>
			<load arg="30"/>
			<push arg="36"/>
			<call arg="82"/>
			<store arg="64"/>
			<load arg="30"/>
			<push arg="38"/>
			<call arg="83"/>
			<store arg="84"/>
			<load arg="84"/>
			<dup/>
			<load arg="7"/>
			<load arg="64"/>
			<get arg="103"/>
			<call arg="65"/>
			<set arg="103"/>
			<dup/>
			<load arg="7"/>
			<load arg="64"/>
			<get arg="104"/>
			<pushi arg="64"/>
			<call arg="105"/>
			<call arg="65"/>
			<set arg="104"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="106" begin="11" end="11"/>
			<lne id="107" begin="11" end="12"/>
			<lne id="108" begin="9" end="14"/>
			<lne id="109" begin="17" end="17"/>
			<lne id="110" begin="17" end="18"/>
			<lne id="111" begin="19" end="19"/>
			<lne id="112" begin="17" end="20"/>
			<lne id="113" begin="15" end="22"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="36" begin="3" end="23"/>
			<lve slot="3" name="38" begin="7" end="23"/>
			<lve slot="0" name="18" begin="0" end="23"/>
			<lve slot="1" name="89" begin="0" end="23"/>
		</localvariabletable>
	</operation>
	<operation name="114">
		<context type="6"/>
		<parameters>
			<parameter name="30" type="81"/>
		</parameters>
		<code>
			<load arg="30"/>
			<push arg="36"/>
			<call arg="82"/>
			<store arg="64"/>
			<load arg="30"/>
			<push arg="38"/>
			<call arg="83"/>
			<store arg="84"/>
			<load arg="84"/>
			<dup/>
			<load arg="7"/>
			<load arg="64"/>
			<get arg="73"/>
			<call arg="65"/>
			<set arg="73"/>
			<dup/>
			<load arg="7"/>
			<load arg="64"/>
			<get arg="115"/>
			<call arg="65"/>
			<set arg="115"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="116" begin="11" end="11"/>
			<lne id="117" begin="11" end="12"/>
			<lne id="118" begin="9" end="14"/>
			<lne id="119" begin="17" end="17"/>
			<lne id="120" begin="17" end="18"/>
			<lne id="121" begin="15" end="20"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="36" begin="3" end="21"/>
			<lve slot="3" name="38" begin="7" end="21"/>
			<lve slot="0" name="18" begin="0" end="21"/>
			<lve slot="1" name="89" begin="0" end="21"/>
		</localvariabletable>
	</operation>
</asm>
