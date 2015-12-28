<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="brand" href="/" style="background: url(/static/img/logo-manage.png) no-repeat;display:block;height:20px;" title="超级系统管理后台"></a>
      <#if _userdetail??>
      <div class="nav-collapse collapse">
       <ul class="nav">
        <li class="dropdown <#if toolbar=='auditor'>active</#if>">
          <a  class="dropdown-toggle" data-toggle="dropdown">
            审核操作 <b class="caret"></b>
          </a>
          <ul class="dropdown-menu">
            <li><a href="/auditor/avatar/">用户审核</a></li>
            <li><a href="/auditor/question/">问题审核</a></li>
            <li><a href="/auditor/answer/">答案审核</a></li>
            <li><a href="/auditor/answerComment/">答案评论审核</a></li>
            <li><a href="/auditor/blogComment/">博文评论审核</a></li>
            <li><a href="/auditor/answerReply/">继续问答审核</a></li>
            <li><a href="/auditor/blog/">博文审核</a></li>
            <li><a href="/auditor/album/">相册审核</a></li>
            <li><a href="/auditor/message/">私信审核 <i class="icon-comment"></i></a></li>
            <li class="divider"></li>
            <#if !_userdetail.belongOneRole('ROLE_VC')>
            <li><a href="/other/feedbacks/">用户反馈</a></li>
            </#if>
          </ul>
        </li>
      </ul>
      <ul class="nav">
        <li class="dropdown">
          <a  class="dropdown-toggle" data-toggle="dropdown">
            查询操作 <b class="caret"></b>
          </a>
          <ul class="dropdown-menu">
            <li><a href="/search/user/">用户查询</a></li>
            <li><a href="/search/questions/">问题查询</a></li>
            <li><a href="/search/questionstickers/">问题标记</a></li>
            <li><a href="/search/blogs/">博文查询</a></li>
            <li><a href="/search/comments/">评论查询</a></li>
            <li class="divider"></li>
            <li><a href="/search/utility/">实用工具集</a></li>
            <li><a href="/op/opfiles/">文件上传</a></li>
            <li><a href="/sms/">发送短信</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav">
        <li class="dropdown <#if toolbar=='config'>active</#if>">
          <a  class="dropdown-toggle" data-toggle="dropdown">
           配置管理 <b class="caret"></b>
         </a>
         <ul class="dropdown-menu" role="menu">
          <li class="dropdown-submenu">
            <a tabindex="-1" >基础配置</a>
            <ul class="dropdown-menu">
              <li><a href="/search/doyen/">达人设置</a></li>
              <li><a href="/search/merchant-doyen/">商家达人</a></li>
              <li><a href="/prop/expert/">推荐达人配置</a></li>
              <li><a href="/prop/ask/">提问页底部配置</a></li>
              <li><a href="/prop/questionRecommend/">问题详情页商品配置</a></li>
              <li><a href="/prop/questionInterest/">问题详情页猜你感兴趣配置</a></li>
              <li><a href="/prop/adUrl/">判断广告url配置</a></li>
              <li><a href="/prop/blogTagList/?category=Track">游记等列表相关配置</a></li>
              <li><a href="/prop/answerAdSign/">答案附带广告配置</a></li>
              <li><a href="/prop/footer/">footer内链配置</a></li>
            </ul>
          </li>
          <li class="dropdown-submenu">
            <a tabindex="-1">首页配置 <i class="icon-th-list"></i></a>
            <ul class="dropdown-menu">
              <li><a href="/prop/indexTopImages/">首页头图轮换设置</a></li>
              <li class="divider"></li>
              <li><a href="/prop/index-story/">首页【世界邦故事】 <i class="icon-medkit"></i></a></li>
              <li><a href="/prop/index-trip/">首页【各国行程推荐】 <i class="icon-medkit"></i></a></li>
              <li><a href="/prop/index-question/">首页【达人专业解答】 <i class="icon-medkit"></i></a></li>
              <li><a href="/prop/index-place/">首页【目的地】 <i class="icon-medkit"></i></a></li>
              <li><a href="/prop/index-blog/">首页【文章推荐】 <i class="icon-medkit"></i></a></li>
            </ul>
          </li>
          <li class="dropdown-submenu">
            <a tabindex="-1">达人管理 <i class="icon-th-list"></i></a>
            <ul class="dropdown-menu">
              <li><a href="/doyen/task/">达人任务管理</a></li>
              <li class="divider"></li>
              <li><a href="/prop/doyen-park/">达人广场配置</a></li>
              <li><a href="/reputation/log/">达人状态（积分日志）</a></li>
            </ul>
          </li>
          <li class="dropdown-submenu">
            <a tabindex="-1" href="/trip/">行程管理 <i class="icon-plane"></i></a>
            <ul class="dropdown-menu">
              <li><a href="/trip/">行程列表</a></li>
              <li><a href="/trip/doa/">DOA(每日)列表</a></li>
              <li><a href="/trip/poa/">POA(活动)列表</a></li>
              <li><a href="/trip/review/trip/">行程审核</a></li>
              <li><a href="/trip/poiapply/">审核POI申请</a></li>
              <li><a href="/trip/roll/">默认轮播配置</a></li>
              <li><a href="/trip/park-location/">广场页国家标签设置</a></li>
              <li><a href="/trip/whitelist/">行程白名单设置</a></li>
              <li><a href="/trip/country/config/">创建行程国家设置</a> </li>
              <li><a href="/trip/hottags/">创建行程热门标签设置</a></li>
              <li><a href="/trip/hotcities/">创建行程热门城市设置</a></li>
              <li><a href="/trip/openeduid/">私密行程用户白名单</a></li>
              <li><a href="/trip/relateddata/">行程扩展配置</a></li>
            </ul>
          </li>
          <li class="dropdown-submenu">
            <a tabindex="-1" >积分管理 <i class="icon-star-empty"></i></a>
            <ul class="dropdown-menu">
              <li><a href="/reputation/">积分修改</a></li>
              <li><a href="/reputation/rank/week/">积分排行配置</a></li>
              <li><a href="/reputation/goods/">积分兑换</a></li>
              <li><a href="/reputation/goods/prop/">贝壳兑换中心banner配置</a></li>
            </ul>
          </li>
          <li class="dropdown-submenu">
            <a tabindex="-1" >目的地 <i class="icon-star-empty"></i></a>
            <ul class="dropdown-menu">
              <li><a href="/prop/place/roll/">目的地轮播</a></li>
              <#-- <li><a href="/prop/place/">目的地国家</a></li> -->
              <li><a href="/prop/place-topplaces/?range=all">目的地总排行</a></li>
              <li><a href="/prop/place-topplaces/?range=week">目的地周排行</a></li>
              <li class="divider"></li>
              <li><a href="/prop/place-traffic/">目的地间交通费</a></li>
            </ul>
          </li>
          <li class="dropdown-submenu">
            <a tabindex="-1">网站控制 <i class="icon-lock"></i></b></a>
            <ul class="dropdown-menu">
              <li><a href="/prop/invite/">用户邀请配置</a></li>
            </ul>
          </li>
        </ul>
      </li>
    </ul>
    <ul class="nav">
      <li class="dropdown">
        <a  class="dropdown-toggle" data-toggle="dropdown">
          统计信息 <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li><a href="/stat/">统计信息</a></li>
          <li><a href="/stat/tasks/">统计任务</a></li>
          <li><a href="/stat/locationview/">地区浏览情况</a></li>
          <li><a href="/stat/beenhereview/">BeenHere浏览情况</a></li>
          <#if !_userdetail.belongOneRole('ROLE_VC')>
          <li><a href="/airusers/">马甲管理</a></li>
          </#if>
        </ul>
      </li>
    </ul>
    <ul class="nav">
      <li class="dropdown <#if toolbar=='prism'>active</#if>">
        <a  class="dropdown-toggle" data-toggle="dropdown">
          核心系统 <b class="caret"></b>
        </a>
        <ul class="dropdown-menu" role="menu">
          <li class="dropdown-submenu">
            <a tabindex="-1">小邦手</a>
            <ul class="dropdown-menu">
              <li><a href="/advisor/" target="_blank" >小邦手运营</a></li>
              <li><a href="/advisor/info/?day=0" target="_blank" >小邦手统计</a></li>
              <#-- <li><a href="/advisor/mobile/version/">配置App版本号</a></li> -->
            </ul>
          </li>
          <li class="dropdown-submenu">
            <a tabindex="-1">棱镜系统 <i class="icon-star-empty"></i></a>
            <ul class="dropdown-menu">
              <li><a href="/prism/trade/">广告管理</a></li>
              <li><a href="/prism/section/">广告位管理</a></li>
              <li><a href="/prism/template/">模板管理</a></li>
            </ul>
          </li>
        </ul>
      </li>
    </ul>

    <#if !_userdetail.belongOneRole('ROLE_VC')>
    <ul class="nav">
      <li class="dropdown">
        <a  class="dropdown-toggle" data-toggle="dropdown">
          合作伙伴 <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li><a href="/partner/so/">360 ONE BOX</a></li>
          <li><a href="/partner/so/rank/">关键词360排名</a></li>
        </ul>
      </li>
    </ul>
    <ul class="nav">
      <li class="dropdown <#if toolbar=='systemadmin'>active</#if>">
        <a class="dropdown-toggle" data-toggle="dropdown">
          系统管理 <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li><a href="/zookeeper/update-status/">缓存管理</a></li>
          <li class="divider"></li>
          <li><a href="/systemadmin/roles/">角色管理</a></li>
          <li><a href="/systemadmin/users/">用户管理</a></li>
          <li><a href="/systemadmin/resources/">资源管理</a></li>
          <li class="divider"></li>
          <li><a href="/systemadmin/logs/">操作日志</a></li>
          <li class="divider"></li>
          <li><a href="/systemadmin/version/">版本信息</a></li>
        </ul>
      </li>
    </ul>
    </#if>
    <ul class="nav pull-right">
      <li id="fat-menu" class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown">
          <i class="icon-user icon-white"></i> ${_userdetail.user.cname!_userdetail.username} <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li><a href="/my/">个人信息</a></li>
          <li class="divider"></li>
          <li><a href="/logout/">退出 <i class="icon-off"></i></a></li>
        </ul>
      </li>
    </ul>


  </div>
  </#if>
</div>
</div>
</div>