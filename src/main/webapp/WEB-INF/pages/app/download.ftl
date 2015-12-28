<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2','/static/js/underscore-min.js?1.5.1']>
<#assign _title="达人广场配置">
<#include "commons/header.ftl" />
<#assign toolbar="config" />
<#include "commons/toolbar.ftl" />

<style type="text/css">
    li.tag{
        padding-right: 20px !important;
        line-height: 28px;
    }
    div.well{padding-left: 30px;}
    div.form-actions{text-align: center;}
</style>

<div class="container">

    <div class="row">
        <h3>短信接收的app下载url配置 </h3>
    </div>
    <form class="form-horizontal" method="post">

    <div class="well">
            <div class="control-group">
                <label class="control-label" for="input01">android手机下载url</label>
                <div class="controls">
                    <textarea name="appAndroidUrl" style="width: 500px;height: 50px;">${appAndroidUrl}</textarea>
                    <p class="help-block">形式如下：(http://f3.sjbly.cn/f14/0905/1414/ShiJieBang_200_Shiji-yls.apk),必须带有http://</p>
                </div>
            </div>

        <div class="control-group">
            <label class="control-label" for="input01">ios手机下载url</label>
            <div class="controls">
                <textarea name="appIosUrl" style="width: 600px;height: 50px;">${appIosUrl}</textarea>
                <p class="help-block">形式如下：(https://itunes.apple.com/cn/app/xing-cheng-da-shi-shi-jie/id847177468?l=de&ls=1&mt=8),必须带有http://</p>
            </div>
        </div>
    </div>







    <div class="form-actions">
        <button type="submit" id="save" class="btn btn-primary">保存</button>
        <#if message??>
        <span  class="text-warning" style="margin-left:20px;">${message}</span>
        </#if>
    </div>
    </form>

</div>



<#include "commons/footer.ftl" />