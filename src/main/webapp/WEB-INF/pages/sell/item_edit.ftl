<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />
<link href="/static/bootstrap-3.3.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

<!-- 配置文件 -->
<script type="text/javascript" src="/static/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="/static/ueditor/ueditor.all.js"></script>
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="/static/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<form class="form-horizontal" method="POST" action="/sell/item/" enctype="multipart/form-data">
<div class="container" >
    <div class="row-fluid">
    <#if msg??>
        <div class="alert text-center">
            <a class="close" data-dismiss="alert">×</a>
        ${msg}
        </div>
    </#if>
    </div>

        <input type="hidden" name="id" value="${item.id}"/>
        <input type="hidden" name="t" value="${t}" id="t"/>

        <fieldset>
            <legend>商品信息  </legend>
            <div >
                <p/>
            </div>

            <div class="control-group">
                <label class="control-label" for="cname">商品名称</label>
                <div class="controls">
                    <input type="text" maxlength="8" class="input-large" id="name" name="name" value="${item.name!}">
                    <p class="help-inline"><strong class="text-error">*</strong>8个字以内</p>
                </div>
            </div>

			<div class="control-group">
				<label class="control-label" for="cname">图片上传</label>
				<div class="controls">
					 <input type="file" id="file1" name="files" value="${item_img[0].img!}"><span class="help-inline">

                    <a rel="popover" data-content="<img src='${item_img[0].img!}'/>" href="${item_img[0].img!}" target="_blank" class="text-error img-link">${item_img[0].img!}</a></span></br>

                     <input type="file" name="files" value="${item_img[1].img!}"><span class="help-inline">

                    <a href="${item_img[1].img!}" class="text-error img-link">${item_img[1].img!}</a></span></br>

                     <input type="file" name="files" value="${item_img[2].img!}"><span class="help-inline">

                    <a href="${item_img[2].img!} class="text-error img-link">${item_img[2].img!}</a></span></br>

                     <input type="file" name="files" value="${item_img[3].img!}"><span class="help-inline">

                    <a href="${item_img[3].img!} class="text-error img-link">${item_img[3].img!}</a></span></br>

				</button>
				
                </div>
		    </div>

            <div class="control-group">
                <label class="control-label" for="alias">产品分类</label>
                <div class="controls">
                    <select id='classify' name="classify">
                    <#list classify as cs>
                        <option value="${cs.id}" <#if ((cs.id))== ((classId))>selected</#if> >${cs.name}</option>
                    </#list>
                    </select>

                    <select id='classify' name="classify">
                    <#list classify as cs>
                        <option value="${cs.id}" <#if ((cs.id))== ((classId))>selected</#if> >${cs.name}</option>
                    </#list>
                    </select>
                    
                    <select name="type2">
                        <option value="激光" >激光</option>
                        <option value="针剂" >针剂</option>
                        <option value="手术" >手术</option>
                    </select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="cname">产品类型</label>
                <div class="controls">
                    <select id='type' name="type">
                    <#list types as type>
                        <option value="${type.id}" <#if ((type.id))== ((item.type.id))>selected</#if> >
                        
                        <#if type=='NORMAL'>普通产品</#if>
                        <#if type=='SPECIAL'>特价产品</#if>
                        <#if type=='SECKILLING'>秒杀产品</#if>
                        
                        </option>
                    </#list>
                    </select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="alias">所属医院</label>
                <div class="controls">
                    <select id='hospital' name="hospital">
                    <#list hospitals as hs>
                        <option value="${hs.id}" <#if ((hs.id))== ((hospitalId))>selected</#if> >${hs.name}</option>
                    </#list>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="alias">是否上线</label>
                <div class="controls">
                    <select id='is_online' name="is_online">
                        <option value="true" <#if true == ((item.online))>selected</#if> >上线</option>
                        <option value="false" <#if false == ((item.online))>selected</#if> >下线</option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="alias">所在城市</label>
                <div class="controls">
                    <select id='city_id' name="city_id">
                        <option value="1" <#if 1== ((cityId))>selected</#if> >北京</option>
                        <option value="3" <#if 3== ((cityId))>selected</#if> >上海</option>
                        <option value="225" <#if 225== ((cityId))>selected</#if> >成都</option>
                        <option value="4" <#if 4== ((cityId))>selected</#if> >重庆</option>
                        <option value="256" <#if 256== ((cityId))>selected</#if> >三亚</option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="lname">团购价格</label>
                <div class="controls">
                    <input type="text" class="input-large" id="price" name="price" value="${item.price!}">
                    <p class="help-inline"><strong class="text-error">*</strong>价格只能为数字</p>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">市场价</label>
                <div class="controls">
                    <input type="text" class="input-large" id="market_price" name="market_price" value="${item.marketPrice!}">
                    <p class="help-inline"><strong class="text-error">*</strong>价格只能为数字</p>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">预付款金额</label>
                <div class="controls">
                    <input type="text" class="input-large" id="online_pay" name="online_pay" value="${item.onlinePay!}">
                    <p class="help-inline"><strong class="text-error">*</strong>预付款金额只能为数字</p>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">产品数量</label>
                <div class="controls">
                    <input type="text" class="input-large" id="total_num" name="total_num" value="${item.totalNum!}">
                    <p class="help-inline"><strong class="text-error">*</strong>产品数量只能为数字</p>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">有效期</label>
                <div class="controls">
                    <div class="input-append date" id="date1" name="date1" data-date="${item.dateStart?string("yyyy-MM-dd")!}" data-date-format="yyyy-mm-dd">
                        <input class="span2" id="dateStart" name="dateStart" size="16" type="text" value="${item.dateStart?string("yyyy-MM-dd")!}">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                    －
                    <div class="input-append date" id="date2" name="date2" data-date="${item.dateEnd?string("yyyy-MM-dd")!}" data-date-format="yyyy-mm-dd">
                        <input class="span2" id="dateEnd" name="dateEnd" size="16" type="text" value="${item.dateEnd?string("yyyy-MM-dd")!}">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                    <p class="help-inline"><strong class="text-error">*</strong></p>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">治疗手段</label>
                <div class="controls">
                    <input type="text" maxlength="10" class="form-control"  id="cure_means" name="cure_means" value="${detail.cureMeans!}"></input>
                	<p class="help-inline"><strong class="text-error">*</strong>10个字以内</p>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="lname">治疗时长</label>
                <div class="controls">
                    <input type="text" maxlength="10" class="form-control"  id="cure_dur" name="cure_dur" value="${detail.cureDur!}"></input>
                	<p class="help-inline"><strong class="text-error">*</strong>10个字以内</p>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="lname">住院治疗</label>
                <div class="controls">
                    
                	<select name="cure_hospital">
                		<option value="1" <#if '需要'== ((item.cure_hosptial))>selected</#if> >需要</option>
                		<option value="0" <#if '不需要'== ((item.cure_hosptial))>selected</#if> >不需要</option>
                	</select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="lname">恢复时间</label>
                <div class="controls">
                    <input type="text" class="input-large" id="recover_dur" name="recover_dur" value="${detail.recoverDur!}">
                    <p class="help-inline"><strong class="text-error">*</strong></p>
                </div>
            </div>

            <div class="control-group">
            <label class="control-label" for="alias">是否可分期</label>
            <div class="controls">
                <select id='fq' name="fq">
                    <option value="1" <#if 1== ((item.fq))>selected</#if>>可分期</option>
                    <option value="0" <#if 0== ((item.fq))>selected</#if> >不可分期</option>
                </select>
            </div>
        </div>

			<div class="control-group">
	            <label class="control-label" for="alias">是否首页推荐</label>
	            <div class="controls">
	                <select id='flag' name="flag">
	                    <option value="0" <#if 0== (item.flag)>selected</#if> >不推荐</option>
	                    <option value="1" <#if 1== (item.flag)>selected</#if> >推荐</option>
	                </select>
	            </div>
	        </div>
	        
            <div class="control-group">
                <label class="control-label" for="lname">优点</label>
                <div class="controls">
                   
                 <!-- 加载编辑器的容器 -->
				  <script id="merit" name="merit" type="text/plain"></script>
				  <!-- 实例化编辑器 -->
				  <script type="text/javascript">
				  	var ue = UE.getEditor('merit', {
				  		initialContent:'${detail.merit!}',
				  		initialFrameWidth:800, //初始化宽度
				  		initialFrameHeight:280 //初始化高度
				  	});
				  </script>
                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="lname">治疗方法</label>
                <div class="controls">
                   
                  <!-- 加载编辑器的容器 -->
				  <script id="cure_method" name="cure_method" type="text/plain"></script>
				  <!-- 实例化编辑器 -->
				  <script type="text/javascript">
				  	var ue = UE.getEditor('cure_method', {
				  		initialContent:'${detail.cureMethod!}',
				  		initialFrameWidth:800, //初始化宽度
				  		initialFrameHeight:280 //初始化高度
				  	});
				  </script>
				  
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">注意事项</label>
                <div class="controls">
                   
                  <!-- 加载编辑器的容器 -->
				  <script id="warnings" name="warnings" type="text/plain"></script>
				  <!-- 实例化编辑器 -->
				  <script type="text/javascript">
				  	var ue = UE.getEditor('warnings', {
				  		initialContent:'${detail.warning!}',
				  		initialFrameWidth:800, //初始化宽度
				  		initialFrameHeight:280 //初始化高度
				  	});
				  </script>
				  
                </div>
            </div>
            
            <div class="control-group">
                <label class="control-label" for="lname">适合人群</label>
                <div class="controls">
                   
                  <!-- 加载编辑器的容器 -->
				  <script id="crowd" name="crowd" type="text/plain"></script>
				  <!-- 实例化编辑器 -->
				  <script type="text/javascript">
				  	var ue = UE.getEditor('crowd', {
				  		initialContent:'${detail.crowd!}',
				  		initialFrameWidth:800, //初始化宽度
				  		initialFrameHeight:280 //初始化高度
				  	});
				  </script>
				  
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">禁忌人群</label>
                <div class="controls">
                   
                	<!-- 加载编辑器的容器 -->
					  <script id="taboo_crowd" name="taboo_crowd" type="text/plain"></script>
					  <!-- 实例化编辑器 -->
					  <script type="text/javascript">
					  	var ue = UE.getEditor('taboo_crowd', {
					  		initialContent:'${detail.tabooCrowd!}',
					  		initialFrameWidth:800, //初始化宽度
					  		initialFrameHeight:280 //初始化高度
					  	});
					  </script>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">治疗次数</label>
                <div class="controls">
                    <input type="text" class="input-large" id="cure_num" name="cure_num" value="${detail.cureNum!}">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">麻醉方法</label>
                <div class="controls">
                    <input type="text" class="input-large" id="anes_method" name="anes_method" value="${detail.anesMethod!}">
                    <p class="help-inline"><strong class="text-error">*</strong></p>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">医师级别</label>
                <div class="controls">
              
                	<select name="doctor_level" id = "doctor_level">
                	    <option value="${detail.doctorLevel!}">${detail.doctorLevel!}</option>
                		<option value="到院自选">到院自选</option>
                		<option value="主治医师">主治医师</option>
                		<option value="主治专家">主治专家</option>
                		<option value="院长级别">院长级别</option>              		
                	</select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">治疗周期</label>
                <div class="controls">
                    <input type="text" class="input-large" id="cure_cycle" name="cure_cycle" value="${detail.cureCycle!}">
                    <p class="help-inline"><strong class="text-error">*</strong></p>
                </div>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary" onclick="return docheck();">保 存</button>
                &nbsp;&nbsp;
                <button type="button" class="btn" onclick="history.back();">取消返回</button>
            </div>
        </fieldset>

</div>
</form>


<script type="text/javascript">
    function docheck() {
		if($("#t").val() == 0){
			if($("#file1").val()==''){
	        	alert("产品图片不能为空");
	        	return false;
	        }
        }
        if($.trim($("#name").val()).length < 1){
            alert("产品名称不能为空");
            return false;
        }
        if($.isNumeric($.trim($("#price").val())) == false){
            alert("价格必须是数字");
            return false;
        }
        if($.isNumeric($.trim($("#market_price").val())) == false){
            alert("价格必须是数字");
            return false;
        }
        if($.isNumeric($.trim($("#online_pay").val())) == false){
            alert("预付款必须是数字");
            return false;
        }
        if($.isNumeric($.trim($("#total_num").val())) == false){
            alert("产品数量必须是数字");
            return false;
        }
        if(parseInt($("#total_num").val()) < 1){
            alert("产品数量须大于1");
            return false;
        }
        if($.trim($("#cure_means").val()).length < 1){
        	alert("治疗手段不能为空");
        	return false;
        }
        if($.trim($("#cure_dur").val()).length < 1){
        	alert("治疗时长不能为空");
        	return false;
        }
        if($.trim($("#recover_dur").val()).length < 1){
        	alert("恢复时间不能为空");
        	return false;
        }
        
        if($.trim($("#cure_num").val()).length < 1){
        	alert("治疗次数不能为空");
        	return false;
        }
        if($.trim($("#anes_method").val()).length < 1){
        	alert("麻醉方法不能为空");
        	return false;
        }
        if($.trim($("#doctor_level").val()).length < 1){
        	alert("医师级别未选择");
        	return false;
        }
        if($.trim($("#cure_cycle").val()).length < 1){
        	alert("治疗周期不能为空");
        	return false;
        }

        return true;
    }

    $('#date1').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
    });
    $('#date2').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $(".img-link").hover(function(){
        $(".img-link").popover({
            html:true
        })

        $(".img-link").popover("show")
    },function(){
        $(".img-link").popover("hide")
    })
</script>

<#include "commons/footer.ftl" />