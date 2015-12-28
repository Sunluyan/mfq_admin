<nav class="navbar navbar-default" style="margin-bottom: 5px;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/sales/">${sysname}<span style="font-size: 30%; color: gray; ">${sysversion}</span></a>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li ${(tab1=='remind')?string('class="active"','')}><a href="/sales/remind/clue/search/">我的提醒</a></li>
        <li ${(tab1=='clue')?string('class="active"','')}><a href="/sales/clue/search/">线索管理</a></li>
        <li ${(tab1=='opportunity')?string('class="active"','')}><a href="/sales/affair/searchwork/">商机管理</a></li>
        <li ${(tab1=='setting')?string('class="active"','')}><a href="/sales/admin/setting/typevalue/list/">基础设置</a></li>
      </ul>
    </div>
  </div>
  <i class="glyphicon glyphicon-resize-full js-resize"></i>
</nav>
<div class="mobile-tooltip-loading hide">
    <span class="text-center"><i class="icon icon-spinner icon-spin"></i> 加载中...</span>
</div>
<div id="mobile-tooltip-template" class="hide">
    <div class="mobile-tooltip-content table-responsive text-center" style="margin: auto auto">
        <p><br>找到<span class="mobile-tooltip-count">count</span>条线索外呼记录(最多显示最近5条)</p>
        <table class="table table-bordered vcenter mobile-tooltip-inner">
            <thead>
            <tr>
                <th>次序</th>
                <th>留电话时间</th>
                <th>留电话页</th>
                <th>负责人</th>
                <th>拨打情况</th>
            </tr>
            </thead>
            <tbody>
            <tr class="mobile-tooltip-record">
                <td class="mobile-tooltip-index">1</td>
                <td class="mobile-tooltip-create">yy-MM-dd HH:mm</td>
                <td class="mobile-tooltip-source">source page</td>
                <td class="mobile-tooltip-sales">sales name</td>
                <td class="mobile-tooltip-conclusion">conclusion</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
