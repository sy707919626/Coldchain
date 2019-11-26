package com.bfby.coldchain.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bfby.coldchain.R;
import com.bfby.coldchain.ui.base.BaseActivity;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/22.
 */
@SuppressLint("Registered")
public class UserAgreementActivity extends BaseActivity {

    @BindView(R.id.image_back_detail_bar)
    ImageView mImageBackDetailBar;
    @BindView(R.id.text_detail_content)
    TextView mTextDetailContent;
    @BindView(R.id.text_detail_right)
    TextView mTextDetailRight;
    @BindView(R.id.title_base_toolbar)
    Toolbar mTitleBaseToolbar;
    @BindView(R.id.detail_bar_title)
    LinearLayout mDetailBarTitle;
    @BindView(R.id.policy_bt)
    TextView mPolicyBt;
    @BindView(R.id.buy_policy_text)
    TextView mBuyPolicyText;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_buy_policy;
    }

    @Override
    protected void init() {
        ImmersionBar.with(this)
                .titleBar(R.id.base_bar_title)
                .navigationBarColor(R.color.toolbarBG)
                .init();

        mPolicyBt.setText("");
        mTextDetailContent.setText("用户服务协议");
        mTextDetailRight.setVisibility(View.GONE);
        String AA = "提示条款\n" +
                "\n" +
                "欢迎您与湖南省共托物流设备租赁有限公司下运营的“载运保物流设备租赁服务平台”（以下简称“载运保”）共同签署本《用户服务协议》（下称“本协议”）并使用载运保平台服务。\n" +
                "\n" +
                "本协议各条款前所列索引关键词仅为帮助您理解该条款表达的主旨之用，不影响或限制本协议条款的含义或解释。为维护您自身权益，建议您仔细阅读各条款具体表述。\n" +
                "\n" +
                "【审慎阅读】您在申请注册流程中点击同意本协议之前，应当认真阅读本协议。请您务必审慎阅读、充分理解各条款内容，特别是免除或者限制责任的条款、法律适用和争议解决条款。免除或者限制责任的条款将以粗体下划线标识，您应重点阅读。 如您对协议有任何疑问，可向载运保平台客服咨询。\n" +
                "\n" +
                "【签约动作】当您按照注册页面提示填写信息，阅读并同意本协议且完成全部注册程序后，即表示您已充分阅读、理解并接受本协议的全部内容，并与共托达成一致，成为载运保平台的用户。阅读本协议的过程中，如果您不同意本协议或其中任何条款约定，您应立即停止注册程序。\n" +
                "\n" +
                "1 定义\n" +
                "\n" +
                "在本协议中，除非上下文有其他的规定，下列表达应作如下解释：\n" +
                "\n" +
                "1.1 载运保平台：指湖南省共托物流设备租赁有限公司运营的互联网平台（http://www.zaiyunbao.com/），以及其在安卓、windows等个人计算机或移动终端系统上的客户端。\n" +
                "\n" +
                "1.2 载运保：指载运保平台的经营者，为湖南省共托物流设备租赁有限公司：\n" +
                "\n" +
                "1.3 载运保物流设备租赁服务：指载运保或其关联方为确保认证用户正常租赁物流设备而提供的服务及采取的措施，包括但不限于提供在线数据处理信息服务、整理、调度、布局、客户服务（用户咨询/投诉）等。\n" +
                "\n" +
                "1.4 用户：指具有法定的相应权利能力和行为能力，能够独立承担法律责任的载运保平台各项服务的使用者。\n" +
                "\n" +
                "1.5 认证用户：指与载运保签订《用户服务协议》并完成注册流程的用户。一个认证用户仅可以拥有一个账户，每个账户对应唯一的用户名。\n" +
                "\n" +
                "1.6 账户：指用户认证时设置的账户名，其账号具有唯一性。\n" +
                "\n" +
                "2 协议范围\n" +
                "\n" +
                "2.1 签约主体\n" +
                "\n" +
                "本协议由您与载运保共同缔结，本协议对您与载运保均具有合同效力。\n" +
                "2.2 补充协议\n" +
                "\n" +
                "本平台法律声明、《载运保供应链隐私规则》、《载运保物流设备租赁协议》与《载运保物流设备租赁隐私规则》为本协议的补充协议，与本协议不可分割且具有同等法律效力。如您使用载运保物流设备租赁服务，视为您同意上述补充协议。\n" +
                "\n" +
                "3 用户注册与认证\n" +
                "\n" +
                "3.1 用户资格\n" +
                "\n" +
                "您确认，在您开始用户认证程序从而具备使用载运保平台服务的资格前，您应具备中华人民共和国法律规定的与您行为相适应的民事行为能力。若您不具备前述与您行为相适应的民事行为能力，则您及您的监护人应依照法律规定承担因此而导致的一切后果。\n" +
                "\n" +
                "3.2 账户说明\n" +
                "\n" +
                "(a) 您作为访客通过手机验证等程序注册载运保平台后，您即成为载运保平台的用户并拥有自己的账户。\n" +
                "\n" +
                "(b) 但是仅在完成了用户认证程序成为载运保平台的认证用户后，您方可使用载运保物流设备租赁服务。\n" +
                "\n" +
                "(c) 当您按照用户认证程序提示填写信息、阅读并同意本协议且完成全部认证程序后，您可获得载运保平台认证用户账户。\n" +
                "\n" +
                "(d) 由于认证用户的账户关联认证用户的信用信息，您不得转让该账户，也不得许可或协助他人使用您的认证用户账户使用载运保物流设备租赁服务，否则由此产生的一切责任均由您和实际使用物流设备人共同承担连带责任，载运保将不承担任何法律责任。\n" +
                "\n" +
                "3.3 认证信息管理\n" +
                "\n" +
                "(a)【信息真实】在用户认证过程中，您应按载运保平台页面的提示准确、真实、完整地提供您的信息。您了解并同意，您有义务保持您提供信息的真实性及有效性。\n" +
                "\n" +
                "(b)【更新维护】如果您的认证信息有所变更，您应当及时更新您提供的信息，载运保将依法不时地对您的信息进行检查核实，您应当配合提供最及时、真实、完整的信息。\n" +
                "\n" +
                "(c) 如载运保按您最后一次提供的信息与您联系未果、您未按载运保的要求及时提供信息、您提供的信息存在明显不实的，您将承担因此对您自身、他人及载运保造成的全部损失与不利后果。\n" +
                "\n" +
                "3.4 开通租赁权限。用户在成功通过用户认证程序后，即可租赁、使用载运保平台所提供的物流设备。\n" +
                "\n" +
                "3.5 上述用户认证的审核和通过均由载运保最终决定。载运保可以根据用户上传的文件对其文件的真实性、合法性进行合理的质询和怀疑，并给与用户不通过认证的结果而无需提供任何原因；用户可以通过再次申请认证或申诉进行重新认证。\n" +
                "\n" +
                "4 账户安全规范\n" +
                "\n" +
                "4.1 您的账户由您自行设置并由您保管，载运保任何时候均不会主动要求您提供您的账户信息。因此，请务必保管好您的账户，账户安全由您自行负责，请确保您在每个上网时段结束时退出登录并以正确步骤离开载运保平台。如果您的账户因您主动泄露或遭受他人攻击、诈骗等行为而造成的任何损失及后果，该等损失和后果均由您自行承担。\n" +
                "\n" +
                "4.2 除载运保存在过错外，您应对您账户项下的所有行为和结果（包括发布信息、披露信息、开放通讯录等）负责，无论该等行为和结果是否对您自身或第三方造成任何损害。\n" +
                "\n" +
                "4.3 如发现任何未经授权使用您账户登录载运保平台或其他可能导致您账户遭窃、遗失的情况，建议您立即通知载运保平台。您理解载运保对您的任何请求采取行动均需要合理时间，除载运保存在过错外，载运保对在采取行动前已经产生的后果不承担任何责任。\n" +
                "\n" +
                "5 载运保平台服务及规范\n" +
                "\n" +
                "5.1 经认证的用户可通过载运保平台使用载运保物流设备租赁服务。\n" +
                "\n" +
                "5.2 您应确保您不会利用载运保平台进行任何违法行为或下述行为：\n" +
                "(a) 利用技术手段故意访问、记录、盗取、传播载运保平台的数据和相关信息；\n" +
                "\n" +
                "(b) 以任何方式侵犯载运保及任何第三方的合法权益；\n" +
                "\n" +
                "(c) 干扰或破坏载运保平台、其服务器或其网络；\n" +
                "\n" +
                "(d) 未经合法授权而截取、篡改、收集、储存、使用、传播或删除其他用户的个人信息或提供的其他信息；\n" +
                "\n" +
                "(e) 其他未经合法授权的行为。\n" +
                "\n" +
                "5.3 您应确保您在载运保平台上所发布和传播的内容不得包含下述信息：\n" +
                "\n" +
                "(a) 违反宪法确定的基本原则的；\n" +
                "\n" +
                "(b) 危害国家统一、主权和领土完整的；\n" +
                "\n" +
                "(c) 泄露国家秘密，危害国家安全，损害国家荣誉和利益的；\n" +
                "\n" +
                "(d) 煽动民族仇恨、民族歧视，破坏民族团结，侵害民族风俗、习惯的；\n" +
                "\n" +
                "(e) 违背国家宗教政策，宣扬邪教、迷信的；\n" +
                "\n" +
                "(f) 扰乱社会秩序，破坏社会稳定的；\n" +
                "\n" +
                "(g) 宣扬淫秽、赌博、暴力、教唆犯罪的；\n" +
                "\n" +
                "(h) 侮辱、诽谤、恐吓、涉及他人隐私等侵害他人合法权益的；\n" +
                "\n" +
                "(i) 侵犯他人知识产权或涉及第三方商业秘密及其他专有权利的；\n" +
                "\n" +
                "(j) 存在可能破坏、篡改、删除、影响载运保平台任何系统正常运行或未经授权秘密获取载运保平台及其他用户的数据、个人资料的病毒、木马、爬虫等恶意软件、程序代码的；\n" +
                "\n" +
                "(k) 危害社会公德，诋毁民族优秀文化的；\n" +
                "\n" +
                "(l) 有国家法律、法规或政策禁止的其他内容。\n" +
                "\n" +
                "5.4 不可抗力\n" +
                "\n" +
                "载运保依法律规定承担基础保障义务，但无法对由于信息网络基础设施、信息网络设备维护、连接故障，电脑、通讯或其他系统的故障，网络信息与数据安全，电力故障，罢工，暴乱，火灾，洪水，风暴，爆炸，战争，政府行为，司法行政机关的命令或因第三方原因而给您造成的损害结果承担责任。\n" +
                "\n" +
                "6 用户信息的保护及授权\n" +
                "\n" +
                "6.1 个人信息的保护\n" +
                "\n" +
                "(a) 载运保非常重视您个人信息和其他用户信息的保护，在您使用载运保平台的服务时，您同意载运保按照在载运保平台上公布的《载运保供应链隐私规则》收集、存储、使用、披露和保护您的个人信息或其他信息。载运保希望通过《载运保供应链隐私规则》向您清楚地介绍载运保对您个人信息\n" +
                "的处理方式，因此载运保建议您完整地阅读《载运保供应链隐私规则》），以帮助您更好地保护您的隐私权。\n" +
                "\n" +
                "(b) 在任何情况下您使用载运保平台，视为您同意遵守《载运保供应链隐私规则》下您的义务，允许载运保行使《载运保供应链隐私规则》下载运保的权利。\n" +
                "\n" +
                "(c) 在您通过载运保平台与他人沟通交流的过程中，如果您接触到其他用户的个人资料、信息、文件等，您保证您将严格保密，不以任何方式向任何第三方进行披露。\n" +
                "\n" +
                "(d) 对于您提供及发布除个人信息外的文字、图片、视频、音频等非个人信息，在版权保护期内您免费授予载运保获得全球的许可使用权。您同意载运保存储、使用、复制、修订、编辑、发布、展示、翻译、分发您的上述信息，并以已知或日后开发的形式、媒体或技术将上述信息纳入其它作品内。\n" +
                "\n" +
                "7 著作权\n" +
                "\n" +
                "7.1 您同意本协议并成为载运保平台用户的行为，仅使得您本人得以按照本协议的规范使用载运保平台的各项服务。您与载运保平台或载运保之间并不存在其他授权、合作、代理、委托、雇佣等关系。\n" +
                "\n" +
                "7.2 您了解载运保平台和相关专有保密资料的知识产权归属于载运保。您了解载运保平台上的任何赞助广告或信息的知识产权归属于相关赞助广告或信息的提供商。您了解并同意，未经知识产权所有人的书面明示授权，您不得对上述知识产权进行修改、出租、出借、出售、传播或实施其他侵犯上述知识产权的行为（例如进行还原工程、反向组译等）。\n" +
                "\n" +
                "8 责任的限制\n" +
                "\n" +
                "由于互联网服务实时更新的性质，您了解并同意，载运保不对下述内容承担任何保证责任，对该等原因对您造成的任何损失，载运保不承担任何责任：\n" +
                "\n" +
                "8.1 不保证载运保平台服务不受任何干扰、服务提供及时、安全可靠、不出现任何差错；\n" +
                "\n" +
                "8.2 不保证使用载运保平台服务所取得的信息在任何情况下均正确可靠；\n" +
                "\n" +
                "8.3 不保证您经由载运保平台所租赁的物流设备符合您的期望；\n" +
                "\n" +
                "8.4 不保证通过载运保平台下载或取得的任何资料不会导致您的个人电脑或移动设备有任何损坏或数据有任何流失。\n" +
                "\n" +
                "载运保负责「按现状」和「可得到」的状态向您提供载运保平台的服务。除本协议内所做出的保证或载运保以书面形式做出的其他明示保证外，载运保未向您做出任何形式的保证或承诺，包括但不限于商业适售性、特定目的之适用性或其他明示或暗示的保证。\n" +
                "\n" +
                "9 使用规则\n" +
                "9.1 您应该通过载运保平台在移动设备上的APP应用程序创建订单；\n" +
                "\n" +
                "9.2 您应该完整、正确地填写您的有效信息（包括姓名、身份证号、联系电话、收货地址等），保证物流设备可以送达目的地；\n" +
                "\n" +
                "9.3 如果您在物流设备入库时发现个别物流设备有破损且影响使用的，请及时向平台反映或联系载运保客服人员；\n" +
                "\n" +
                "9.4 您在使用物流设备时请按正确的使用方法操作，如果您在使用过程中因不合理操作造成原本完好的物流设备破损、开裂或无法继续使用，您将承担相应的赔偿责任；\n" +
                "\n" +
                "9.5 您通过平台租赁的物流设备仅限自己使用，严禁转租或转借给他人使用，否则由此造成的纠纷和损失，都将由您自行承担；\n" +
                "\n" +
                "9.6 您在使用物流设备期间如与第三方发生纠纷应由纠纷双方自行解决，载运保不承担任何相关赔偿义务，如给载运保造成损失的，您应向载运保承担赔偿义务；\n" +
                "\n" +
                "9.7 您应通过载运保平台在移动设备上的APP应用程序结束订单，退还物流设备并缴纳相应费用；\n" +
                "\n" +
                "9.8 如设备丢失，客户须按折旧计算相应费用赔偿载运保（折旧按使用年限及使用次数等因素计，按照平均年限法，和使用次数折旧法计算）；如设备因使用过程中操作不得当损坏，将由客户赔偿相应修理费金额，如设备损毁或者丢失将由客户赔偿相应采购设备的金额（按照平均年限法，和使用次数折旧法计算设备价值）。\n" +
                "\n" +
                "10 用户的行为的评价及处理\n" +
                "\n" +
                "10.1 跟踪评价\n" +
                "\n" +
                "为了向用户提供更加优质的服务，且为维持市场正常运营秩序，载运保有权对您使用载运保物流设备租赁服务的行为，进行跟踪监测和评价。\n" +
                "\n" +
                "10.2 违约认定\n" +
                "\n" +
                "发生如下情形之一的，视为您违约：\n" +
                "\n" +
                "(a) 如载运保在用户认证信息复核过程中发现用户提供的认证信息不全、无效或虚假；\n" +
                "\n" +
                "(b) 如用户发生危及交易安全或账户安全的行为；\n" +
                "\n" +
                "(c) 如用户采用不正当手段谋取利益的行为，包括向载运保工作人员及/或其关联人士提供财物、消费、款待或商业机会，或通过其他手段谋取不正当利益；\n" +
                "\n" +
                "(d) 如用户扰乱载运保平台的秩序，以任何方式，刻意规避载运保平台的各类规则或市场管控措施，或以不正当的方式获取、使用载运保供应链管理平台资源的行为；\n" +
                "\n" +
                "(e) 如用户违反本协议第9条规定的使用规则；\n" +
                "\n" +
                "(f) 如用户违反中国相关法律法规的规定；\n" +
                "\n" +
                "10.3 为保障其他用户或载运保平台的正当权益，维持市场正常运营秩序，在用户违规处理期间载运保按照本协议规定的以下情形对用户采取违规处理措施，载运保没有义务在采取违规处理前通知用户：\n" +
                "\n" +
                "(a) 停止载运保物流设备租赁服务：指停止认证用户通过载运保平台租赁使用物流设备的权利；\n" +
                "\n" +
                "(b) 关闭账户：指删除用户的账户或停止用户的所有权限，并将用户列入黑名单，不再向用户提供任何服务；\n" +
                "\n" +
                "(c) 公示警告：指在载运保平台的管理系统等位置对其正在被执行的处理进行公示；\n" +
                "\n" +
                "(d) 终止本协议。\n" +
                "\n" +
                "10.4 载运保可将对您上述违约行为处理措施信息以及其他经国家行政或司法机关生效法律文书确认的违法信息在载运保平台上予以公示。\n" +
                "\n" +
                "10.5 赔偿责任\n" +
                "(a) 如您的行为使载运保遭受损失（包括自身的直接经济损失、商誉损失及对外支付的赔偿金、和解款、律师费、诉讼费等间接经济损失），您应赔偿载运保的上述全部损失。\n" +
                "\n" +
                "(b) 如您的行为使载运保遭受第三人主张权利，载运保可在对第三人承担金钱给付等义务后就全部损失向您追偿。\n" +
                "\n" +
                "10.6 如您向载运保的雇员或顾问等提供实物、现金、现金等价物、劳务、旅游等价值明显超出正常商务洽谈范畴的利益，则可视为您存在商业贿赂行为。发生上述情形的，载运保可立即终止与您的所有合作并向您收取违约金及/或赔偿金，该等金额以载运保因您的贿赂行为而遭受的经济损失和商誉损失作为计算依据。\n" +
                "\n" +
                "11 协议的变更\n" +
                "\n" +
                "11.1 由于互联网的高速发展，载运保保留不经事先通知为维修、升级或其他目的暂停或更改本服务任何部分的权利。\n" +
                "\n" +
                "11.2 载运保可根据国家法律法规变化及维护交易秩序、保护消费者权益等需要，不时修改本协议、补充协议，变更后的协议、补充协议（下称“变更事项”）将通过法定程序并以本协议第12条约定的方式通知您。\n" +
                "\n" +
                "11.3 如您不同意变更事项，您有权于变更事项确定的生效日前联系载运保反馈意见。如反馈意见得以采纳，载运保将酌情调整变更事项。\n" +
                "\n" +
                "11.4 如您对已生效的变更事项仍不同意的，您应当于变更事项确定的生效之日起停止使用载运保平台服务，变更事项对您不产生效力；如您在变更事项生效后仍继续使用载运保平台服务，则视为您同意已生效的变更事项。\n" +
                "\n" +
                "12 通知\n" +
                "\n" +
                "您同意载运保以以下合理的方式向您送达各类通知：\n" +
                "\n" +
                "(a) 载运保平台公示的文案；\n" +
                "\n" +
                "(b) 站内短信、载运保平台弹出消息、客户端推送的消息；\n" +
                "\n" +
                "(c) 根据您预留于载运保平台的联系方式发出的电子邮件、短信、函件等。\n" +
                "\n" +
                "13 协议的终止\n" +
                "\n" +
                "13.1 用户发起的终止\n" +
                "\n" +
                "您有权通过以下任一方式终止本协议：\n" +
                "\n" +
                "(a) 您不再继续使用载运保平台服务，后台将会冻结您的账户；\n" +
                "\n" +
                "(b) 变更事项生效前您停止使用并明示不愿接受变更事项的。\n" +
                "\n" +
                "13.2 载运保发起的终止\n" +
                "\n" +
                "出现以下情况时，载运保可以本协议第12条的所列的方式通知您终止本协议：\n" +
                "\n" +
                "(a) 您违反本协议约定，载运保依据违约条款终止本协议的；\n" +
                "\n" +
                "(b) 您的用户行为存在异常，载运保合理怀疑您可能存在违反本协议约定的行为，如果不终止本协议则可能对载运保平台或其他用户造成不利影响的；\n" +
                "\n" +
                "(c) 您盗用他人账户、发布违禁信息、骗取他人财物、采取不正当手段谋利等行为，载运保依据本协议对您的账户予以查封的；\n" +
                "\n" +
                "(d) 除上述情形外，因您多次违反载运保平台相关规定且情节严重，载运保依据本协议对您的账户予以查封的；\n" +
                "\n" +
                "(e) 其它应当终止服务的情况。\n" +
                "\n" +
                "13.3 协议终止后的处理\n" +
                "\n" +
                "(a) 本协议终止后，除法律有明确规定外，载运保无义务向您或您指定的第三方披露您账户中的任何信息。\n" +
                "\n" +
                "(b) 本协议终止后，载运保仍享有下列权利：\n" +
                "\n" +
                "(i) 继续保存您留存于载运保平台的各类信息；\n" +
                "\n" +
                "(ii) 对于您过往的违约行为，载运保仍可依据本协议向您追究违约责任。\n" +
                "\n" +
                "14 法律适用、管辖与其他\n" +
                "\n" +
                "14.1【法律适用】本协议之订立、生效、解释、修订、补充、终止、执行与争议解决均适用中华人民共和国大陆地区法律；如法律无相关规定的，参照商业惯例及/或行业惯例。\n" +
                "\n" +
                "14.2【管辖】您因使用载运保平台服务所产生及与载运保平台服务有关的争议，由载运保与您协商解决。协商不成时，任何一方均有权向杭州市萧山区人民法院起诉。\n" +
                "\n" +
                "14.3【可分性】本协议任一条款被视为废止、无效或不可执行，该条应视为可分的且并不影响本协议其余条款的有效性及可执行性。\n" +
                "\n" +
                "14.4【标题】本协议的标题仅供阅读方便而设，不属于协议的条款，不具有任何法律效果。\n" +
                "\n" +
                "14.5【非弃权】任何一方当事人放弃或者延迟行使其在本协议项下的全部或部分权利的，不应视为其放弃本协议项下的任何其他权利或与此类似的一切权利。\n" +
                "\n" +
                "14.6【完全合意】本协议构成各方之间就本协议内容所达成的完全合意；本协议签署前存在的、或与本协议相矛盾或抵触的一切合意，无论其为口头、书面或其他形式均归于无效。\n" +
                "\n" +
                "14.7【部分条款无效】如果本协议及其附件的任何条款因适用法律而无效或不可强制执行，则该条款应视为自始无效，且不影响本协议其他条款的效力。在此情形下，各方应当在合法范围内协商确定新的条款，以保证最大限度地实现原有条款的意图。\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n";

        mBuyPolicyText.setText(AA);
    }
}
