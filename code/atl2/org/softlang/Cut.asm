<?xml version = '1.0' encoding = 'ISO-8859-1' ?>
<asm version="1.0" name="0">
	<cp>
		<constant value="Cut"/>
		<constant value="links"/>
		<constant value="NTransientLinkSet;"/>
		<constant value="col"/>
		<constant value="J"/>
		<constant value="main"/>
		<constant value="A"/>
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
		<constant value="__resolve__"/>
		<constant value="1"/>
		<constant value="J.oclIsKindOf(J):B"/>
		<constant value="18"/>
		<constant value="NTransientLinkSet;.getLinkBySourceElement(S):QNTransientLink;"/>
		<constant value="J.oclIsUndefined():B"/>
		<constant value="15"/>
		<constant value="NTransientLink;.getTargetFromSource(J):J"/>
		<constant value="17"/>
		<constant value="30"/>
		<constant value="Sequence"/>
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
		<constant value="__matcher__"/>
		<constant value="A.__matchCompany2Company():V"/>
		<constant value="A.__matchDept2Dept():V"/>
		<constant value="A.__matchEmployee2Employee():V"/>
		<constant value="A.__matchPerson2Person():V"/>
		<constant value="__exec__"/>
		<constant value="Company2Company"/>
		<constant value="NTransientLinkSet;.getLinksByRule(S):QNTransientLink;"/>
		<constant value="A.__applyCompany2Company(NTransientLink;):V"/>
		<constant value="Dept2Dept"/>
		<constant value="A.__applyDept2Dept(NTransientLink;):V"/>
		<constant value="Employee2Employee"/>
		<constant value="A.__applyEmployee2Employee(NTransientLink;):V"/>
		<constant value="Person2Person"/>
		<constant value="A.__applyPerson2Person(NTransientLink;):V"/>
		<constant value="__matchCompany2Company"/>
		<constant value="Company"/>
		<constant value="IN"/>
		<constant value="MMOF!Classifier;.allInstancesFrom(S):QJ"/>
		<constant value="TransientLink"/>
		<constant value="NTransientLink;.setRule(MATL!Rule;):V"/>
		<constant value="s"/>
		<constant value="NTransientLink;.addSourceElement(SJ):V"/>
		<constant value="t"/>
		<constant value="NTransientLink;.addTargetElement(SJ):V"/>
		<constant value="NTransientLinkSet;.addLink2(NTransientLink;B):V"/>
		<constant value="11:3-13:4"/>
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
		<constant value="__matchDept2Dept"/>
		<constant value="Dept"/>
		<constant value="21:3-25:4"/>
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
		<constant value="__matchEmployee2Employee"/>
		<constant value="Employee"/>
		<constant value="34:3-37:4"/>
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
		<constant value="__matchPerson2Person"/>
		<constant value="Person"/>
		<constant value="45:3-48:4"/>
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
			<getasm/>
			<push arg="7"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="9"/>
			<call arg="10"/>
			<dup/>
			<push arg="11"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="12"/>
			<call arg="10"/>
			<call arg="13"/>
			<set arg="3"/>
			<getasm/>
			<push arg="14"/>
			<push arg="8"/>
			<new/>
			<set arg="1"/>
			<getasm/>
			<call arg="15"/>
			<getasm/>
			<call arg="16"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="17" begin="0" end="24"/>
		</localvariabletable>
	</operation>
	<operation name="18">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="4"/>
		</parameters>
		<code>
			<load arg="19"/>
			<getasm/>
			<get arg="3"/>
			<call arg="20"/>
			<if arg="21"/>
			<getasm/>
			<get arg="1"/>
			<load arg="19"/>
			<call arg="22"/>
			<dup/>
			<call arg="23"/>
			<if arg="24"/>
			<load arg="19"/>
			<call arg="25"/>
			<goto arg="26"/>
			<pop/>
			<load arg="19"/>
			<goto arg="27"/>
			<push arg="28"/>
			<push arg="8"/>
			<new/>
			<load arg="19"/>
			<iterate/>
			<store arg="29"/>
			<getasm/>
			<load arg="29"/>
			<call arg="30"/>
			<call arg="31"/>
			<enditerate/>
			<call arg="32"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="33" begin="23" end="27"/>
			<lve slot="0" name="17" begin="0" end="29"/>
			<lve slot="1" name="34" begin="0" end="29"/>
		</localvariabletable>
	</operation>
	<operation name="35">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="4"/>
			<parameter name="29" type="36"/>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<load arg="19"/>
			<call arg="22"/>
			<load arg="19"/>
			<load arg="29"/>
			<call arg="37"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="17" begin="0" end="6"/>
			<lve slot="1" name="34" begin="0" end="6"/>
			<lve slot="2" name="38" begin="0" end="6"/>
		</localvariabletable>
	</operation>
	<operation name="39">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<call arg="40"/>
			<getasm/>
			<call arg="41"/>
			<getasm/>
			<call arg="42"/>
			<getasm/>
			<call arg="43"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="17" begin="0" end="7"/>
		</localvariabletable>
	</operation>
	<operation name="44">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<push arg="45"/>
			<call arg="46"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<load arg="19"/>
			<call arg="47"/>
			<enditerate/>
			<getasm/>
			<get arg="1"/>
			<push arg="48"/>
			<call arg="46"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<load arg="19"/>
			<call arg="49"/>
			<enditerate/>
			<getasm/>
			<get arg="1"/>
			<push arg="50"/>
			<call arg="46"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<load arg="19"/>
			<call arg="51"/>
			<enditerate/>
			<getasm/>
			<get arg="1"/>
			<push arg="52"/>
			<call arg="46"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<load arg="19"/>
			<call arg="53"/>
			<enditerate/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="33" begin="5" end="8"/>
			<lve slot="1" name="33" begin="15" end="18"/>
			<lve slot="1" name="33" begin="25" end="28"/>
			<lve slot="1" name="33" begin="35" end="38"/>
			<lve slot="0" name="17" begin="0" end="39"/>
		</localvariabletable>
	</operation>
	<operation name="54">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="55"/>
			<push arg="55"/>
			<findme/>
			<push arg="56"/>
			<call arg="57"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<get arg="1"/>
			<push arg="58"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="45"/>
			<call arg="59"/>
			<dup/>
			<push arg="60"/>
			<load arg="19"/>
			<call arg="61"/>
			<dup/>
			<push arg="62"/>
			<push arg="55"/>
			<push arg="55"/>
			<new/>
			<call arg="63"/>
			<pusht/>
			<call arg="64"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="65" begin="19" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="60" begin="6" end="26"/>
			<lve slot="0" name="17" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="66">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="67"/>
		</parameters>
		<code>
			<load arg="19"/>
			<push arg="60"/>
			<call arg="68"/>
			<store arg="29"/>
			<load arg="19"/>
			<push arg="62"/>
			<call arg="69"/>
			<store arg="70"/>
			<load arg="70"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<get arg="71"/>
			<call arg="30"/>
			<set arg="71"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="72" begin="11" end="11"/>
			<lne id="73" begin="11" end="12"/>
			<lne id="74" begin="9" end="14"/>
			<lne id="65" begin="8" end="15"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="3" name="62" begin="7" end="15"/>
			<lve slot="2" name="60" begin="3" end="15"/>
			<lve slot="0" name="17" begin="0" end="15"/>
			<lve slot="1" name="75" begin="0" end="15"/>
		</localvariabletable>
	</operation>
	<operation name="76">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="77"/>
			<push arg="55"/>
			<findme/>
			<push arg="56"/>
			<call arg="57"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<get arg="1"/>
			<push arg="58"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="48"/>
			<call arg="59"/>
			<dup/>
			<push arg="60"/>
			<load arg="19"/>
			<call arg="61"/>
			<dup/>
			<push arg="62"/>
			<push arg="77"/>
			<push arg="55"/>
			<new/>
			<call arg="63"/>
			<pusht/>
			<call arg="64"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="78" begin="19" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="60" begin="6" end="26"/>
			<lve slot="0" name="17" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="79">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="67"/>
		</parameters>
		<code>
			<load arg="19"/>
			<push arg="60"/>
			<call arg="68"/>
			<store arg="29"/>
			<load arg="19"/>
			<push arg="62"/>
			<call arg="69"/>
			<store arg="70"/>
			<load arg="70"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<get arg="38"/>
			<call arg="30"/>
			<set arg="38"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<get arg="80"/>
			<call arg="30"/>
			<set arg="80"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<get arg="81"/>
			<call arg="30"/>
			<set arg="81"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="82" begin="11" end="11"/>
			<lne id="83" begin="11" end="12"/>
			<lne id="84" begin="9" end="14"/>
			<lne id="85" begin="17" end="17"/>
			<lne id="86" begin="17" end="18"/>
			<lne id="87" begin="15" end="20"/>
			<lne id="88" begin="23" end="23"/>
			<lne id="89" begin="23" end="24"/>
			<lne id="90" begin="21" end="26"/>
			<lne id="78" begin="8" end="27"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="3" name="62" begin="7" end="27"/>
			<lve slot="2" name="60" begin="3" end="27"/>
			<lve slot="0" name="17" begin="0" end="27"/>
			<lve slot="1" name="75" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="91">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="92"/>
			<push arg="55"/>
			<findme/>
			<push arg="56"/>
			<call arg="57"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<get arg="1"/>
			<push arg="58"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="50"/>
			<call arg="59"/>
			<dup/>
			<push arg="60"/>
			<load arg="19"/>
			<call arg="61"/>
			<dup/>
			<push arg="62"/>
			<push arg="92"/>
			<push arg="55"/>
			<new/>
			<call arg="63"/>
			<pusht/>
			<call arg="64"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="93" begin="19" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="60" begin="6" end="26"/>
			<lve slot="0" name="17" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="94">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="67"/>
		</parameters>
		<code>
			<load arg="19"/>
			<push arg="60"/>
			<call arg="68"/>
			<store arg="29"/>
			<load arg="19"/>
			<push arg="62"/>
			<call arg="69"/>
			<store arg="70"/>
			<load arg="70"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<get arg="95"/>
			<call arg="30"/>
			<set arg="95"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<get arg="96"/>
			<pushi arg="29"/>
			<call arg="97"/>
			<call arg="30"/>
			<set arg="96"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="98" begin="11" end="11"/>
			<lne id="99" begin="11" end="12"/>
			<lne id="100" begin="9" end="14"/>
			<lne id="101" begin="17" end="17"/>
			<lne id="102" begin="17" end="18"/>
			<lne id="103" begin="19" end="19"/>
			<lne id="104" begin="17" end="20"/>
			<lne id="105" begin="15" end="22"/>
			<lne id="93" begin="8" end="23"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="3" name="62" begin="7" end="23"/>
			<lve slot="2" name="60" begin="3" end="23"/>
			<lve slot="0" name="17" begin="0" end="23"/>
			<lve slot="1" name="75" begin="0" end="23"/>
		</localvariabletable>
	</operation>
	<operation name="106">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="107"/>
			<push arg="55"/>
			<findme/>
			<push arg="56"/>
			<call arg="57"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<get arg="1"/>
			<push arg="58"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="52"/>
			<call arg="59"/>
			<dup/>
			<push arg="60"/>
			<load arg="19"/>
			<call arg="61"/>
			<dup/>
			<push arg="62"/>
			<push arg="107"/>
			<push arg="55"/>
			<new/>
			<call arg="63"/>
			<pusht/>
			<call arg="64"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="108" begin="19" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="60" begin="6" end="26"/>
			<lve slot="0" name="17" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="109">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="67"/>
		</parameters>
		<code>
			<load arg="19"/>
			<push arg="60"/>
			<call arg="68"/>
			<store arg="29"/>
			<load arg="19"/>
			<push arg="62"/>
			<call arg="69"/>
			<store arg="70"/>
			<load arg="70"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<get arg="38"/>
			<call arg="30"/>
			<set arg="38"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<get arg="110"/>
			<call arg="30"/>
			<set arg="110"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="111" begin="11" end="11"/>
			<lne id="112" begin="11" end="12"/>
			<lne id="113" begin="9" end="14"/>
			<lne id="114" begin="17" end="17"/>
			<lne id="115" begin="17" end="18"/>
			<lne id="116" begin="15" end="20"/>
			<lne id="108" begin="8" end="21"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="3" name="62" begin="7" end="21"/>
			<lve slot="2" name="60" begin="3" end="21"/>
			<lve slot="0" name="17" begin="0" end="21"/>
			<lve slot="1" name="75" begin="0" end="21"/>
		</localvariabletable>
	</operation>
</asm>
