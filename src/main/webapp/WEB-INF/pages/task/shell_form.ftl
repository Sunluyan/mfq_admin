<#assign _title="导出excel">
<#include "commons/header.ftl" />
<#assign toolbar="advisor" />
<#include "/commons/toolbar.ftl" />
<#include "/advisor/timemacro.ftl"/>


<div class="container-fluid" id="enlarge-body">
    <div class="container-fluid">

            <br />
            <br />
            <br />
            <div>


                <form action="/task/tripshell/excel/">

                    <div class="control-group">
                        <label class="control-label" >起始时间</label>
                        <div class="controls">
                            <input type="text" class="input" id="start" name="start"  placeholder="">
                            <p class="help-inline" >导出开始时间，样式：2014-07-25（时间是用户开始做壳时间）</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" >结束时间</label>
                        <div class="controls">
                            <input type="text" class="input" id="start" name="end"  placeholder="">
                            <p class="help-inline" >不填写，自动默认为今天</p>
                        </div>
                    </div>
                    <input type="submit" value="导出">
                </form>

            </div>






    </div>
</div>


<#include "/commons/footer.ftl" />
