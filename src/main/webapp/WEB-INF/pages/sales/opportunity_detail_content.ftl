<div class="panel-heading">
    <strong>用户信息</strong>
</div>
<div class="panel-body">
    <table class="table table-condensed vcenter table-yunhu">
    <#if clueTaskInfo.backupMobile??>
    	<#assign backupMobile=clueTaskInfo.backupMobile?split(',')>
    </#if>
    	<tr>
    		<td>UID:<#if curOpp.uid gt 0>${curOpp.uid}</#if></td>
    		<td>用户名:<#if curOpp.uid gt 0>${nick}</#if></td>
            <td>手机号:${curOpp.mobile}
                <a class="btn btn-success btn-xs call-400-label" data-phone-number="${clueTaskInfo.mobile}">拨打电话</a>
            </td>
            <td>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-inline">
                    <div class="form-group">
		                <label for="promotion">推广来源： </label>
                        <span>
		        	    <#assign ppindex=0>
		        	    <input type="hidden" id="promotion" value="${clueTaskInfo.promotion}">
		        	    <#list pptypes as p>
		        	    	<#if p.val=clueTaskInfo.promotion>${p.name}<#assign ppindex=p_index><#break></#if>
		        	    </#list>
                	    <input type="hidden" id="promotionValue" value="${clueTaskInfo.promotionValue}">     
		        	    <#if pptypes?size gt 0>
		        	    	<#list pctypes[''+pptypes[ppindex].val] as c>
		        	    		<#if c.val=clueTaskInfo.promotionValue>- ${c.name}<#break></#if>
		        	    	</#list>
		        	    </#if>
                        </span>
                    </div> 
                </div>
            </td>
            <td>
                <div class="form-inline">
                    <div class="form-group">
	                	<label for="name">用户称呼：</label>
	                	<input type="text" class="form-control input-sm" id="name" value="${clueTaskInfo.name?html}" size="10">
	                </div>
                </div>
            </td>
            <td>
                <div class="form-inline">
                    <div class="form-group">
            	    	<label for="backupMobile1">备用电话：</label>
                        <input type="text" class="form-control input-sm " id="backupMobile1" value="${backupMobile[0]!''}" size="15">
                        <a class="btn btn-success btn-xs btn-sm call-400-label" data-task-info-id="${clueTaskInfo.id}"
            	    		<#if backupMobile[0]>data-phone-number="${backupMobile[0]!''}"<#else>disabled</#if>>
            	    				 拨打电话</a>
            	    </div>
                </div>
            </td>
            <td>
            	<#if !readonly>
                <button type="submit" class="btn btn-xs btn-info" onclick="javascript: sales.updateTaskInfo(this, ${clueTaskInfo.id});">保存修改</button>
            	</#if>
            </td>
        </tr>
    </table>
</div>
       	
<ul class="nav nav-tabs">	
  <#list oppList as opp>
    <li role="presentation" ${(opp.id=curOpp.id)?string('class="active"','')}>
        <#if oppIdsWithOrder?? && oppIdsWithOrder?seq_contains(opp.id)>
        <img class="icon-yunhu-order" src="http://f5.sjbly.cn/y15/0614/2157/00sqm_o.png" width="30">
        </#if>
    	<#if !readonly>
    		<a href="/sales/opportunity/detail/?oid=${opp.id}<#if refOrderId?? && refOrderId!=''>&refOrderId=${refOrderId}</#if>">${opp.name}</a>
	  	<#else>
	  		<a href="/sales/opportunity/searchothers/?t=${searchType}&q=${queryCond}&oid=${opp.id}">${opp.name}</a>
	  	</#if>
	  	<#if opp.id=curOpp.id>
	  		<#assign oppIndex=opp_index>
	  	</#if>
    </li>
  </#list>
</ul>

<#list affairList as affair>
	<#if OrderStatus.LOST.value=affair.orderStatus>
  		<#assign hasLost=true>
  	</#if>
</#list>

<#if !(oppIndex??)><#assign oppIndex=0></#if>
<p>商机受理时间：${curOpp.createdAt?datetime?string('yyyy-MM-dd HH:mm:ss')}</p>
<table class="table table-striped table-bordered table-condensed vcenter table-yunhu">
	<tr>
		<td>主销售负责人：${(salesUsers['u'+oppList[oppIndex].salesId].name)!''} | 辅销售负责人：${(salesUsers['u'+oppList[oppIndex].salesAssistId].name)!''}</td>
		<td style="text-align: right;">
			<#if curOpp.clueId gt 0>
			<a href="/sales/clue/detail/?taskInfoId=${curOpp.clueId}<#if refOrderId?? && refOrderId!=''>&refOrderId=${refOrderId}</#if>" target="_blank"  class="link">线索详情</a>
			<#else>
			老数据无线索
			</#if>
			<#if !readonly && !hasLost>
			<a class="btn btn-info btn-xs" href="/crm/business/form/?uid=${curOpp.uid}&oid=${oppList[oppIndex].id}&ref=sales" class="btn btn-info btn-sm">关联A计划</a>
			<a class="btn btn-info btn-xs" href="javascript:;" onclick="javascript: sales.createAdvisor('${nick}', '${oppList[oppIndex].uid}', '${oppList[oppIndex].id}');" class="btn btn-info btn-sm">关联B计划</a>
			</#if>
		</td>
	</tr>
</table>

<ul class="nav nav-pills">
<#list affairList as affair>
  <li role="presentation" ${(affair.id=curAffairId)?string('class="active"','')}>
    <#if affair.orderStatus=OrderStatus.DEALT.value || affair.orderStatus=OrderStatus.DONE.value>
    <img class="icon-yunhu-order" src="http://f5.sjbly.cn/y15/0614/2157/00sqm_o.png" width="30px">
    </#if>
  	<#if !readonly>
	<a href="/sales/opportunity/detail/?oid=${curOpp.id}&aid=${affair.id}<#if refOrderId?? && refOrderId!=''>&refOrderId=${refOrderId}</#if>" >${affair.name}</a>
	<#else>
	<a href="/sales/opportunity/searchothers/?t=${searchType}&q=${queryCond}&oid=${curOpp.id}&aid=${affair.id}">${affair.name}</a>
	</#if>
	<#if affair.id=curAffairId>
		<#assign affairIndex=affair_index>
	</#if>
	<#if OrderStatus.isAfterDealt(affair.orderStatus)>
		<#assign anyAffairAfterDealt=true>
	</#if>
  </li>
</#list>
</ul>
<#if !(affairIndex??)><#assign affairIndex=0></#if>
<#if affairList?? && (affairList?size > 0)>
<#assign curAffair=affairList[affairIndex]>
<div class="panel panel-info panel-yunhu panel-opportunity">
  <div class="panel-heading">
    事务建立时间：${curAffair.createdAt?datetime?string('yyyy-MM-dd HH:mm:ss')}
  </div>
  <div class="panel-body">
	<table class="table table-striped table-bordered table-condensed vcenter form-inline table-yunhu">
		<tr>
			<td class="col-sm-1">事务名称：</td>
			<td class="col-sm-5"><input type="text" class="form-control" id="affairName" size="50" value="${curAffair.name?html}"></td>
			<td class="col-sm-1">事务类型：</td>
			<td><#if curAffair.type??>${curAffair.type.desc}</#if></td>
		</tr>
		<tr>
			<td>事务状态：</td>
			<td>
				<select class="form-control" id="orderStatus">
					<#assign curStatus=OrderStatus.findByValue(curAffair.orderStatus)>
					<#list OrderStatus.values() as os>
						<#if (os.affairType == null || os.affairType == curAffair.type) && (os.value=curAffair.orderStatus || (curStatus?? && !curStatus.end && curStatus!=OrderStatus.DEALT && os.manual))>
						<option value="${os.value}" ${(os.value=curAffair.orderStatus)?string('selected="selected"', '')}>${os.desc}</option>
						</#if>
					</#list>
				</select>
			</td>
			<td><span class="js-orderStatus-target">丢单原因：</td>
			<td>
				<select class="form-control js-orderStatus-target" id="lostReason">
				<#assign curLostReasonType=0>
				<option value="0">请选择</option>
				<#list LostReasonType.values() as lrt>
				<#if lostReasonMap[lrt]?? && lostReasonMap[lrt]?size gt 0>
				<#if curLostReasonType != lrt>
				<option value="">--------------------------------</option>
				<#assign curLostReasonType=lrt.value>
				</#if>
				<#list lostReasonMap[lrt] as lr>
					<option value="${lr.value}" ${(lr==curAffair.lostReason)?string('selected="selected"', '')}>${lrt.desc}-${lr.desc}</option>
				</#list>
				</#if>
				</#list>
				</select>
			</td>
		</tr>
		<tr>
			<td>是否标星：</td>
			<td>
				<#assign orderRateNum=curAffair.orderRate>
				<#if curAffair.orderRate <= 3><#assign orderRateNum=3><#elseif curAffair.orderRate gte 4><#assign orderRateNum=5></#if>
                <label>
                    <input type="checkbox" id="orderRate" ${(orderRateNum==5)?string('checked="checked"', '')}>
                    <span class="small">（星标用户是重点可成单用户）</span>
                </label>	
			</td>
			<td>用户需求：</td>
			<td>
				<#if (curAffair.type=AffairType.PLAN_A)>
				<#if curAffair.demandId gt 0>
				<a href="/crm/business/form/?uid=${oppList[oppIndex].uid}&oid=${oppList[oppIndex].id}&bid=${curAffair.demandId}&aid=${curAffair.id}&ref=sales" target="_blank">需求清单</a>
				</#if>
				${planATripName!''}
				<#if curAffair.packId gt 0>
				<a href="http://www.shijiebang.com/super/${curAffair.packId}/" target="_blank">A计划详情</a>
				</#if>
				</#if>
				<#if (curAffair.type=AffairType.PLAN_B)>
				<a href="/demand/?refId=${curAffair.advisorId}&ref=sales&oid=${oppList[oppIndex].id}&aid=${curAffair.id}" target="_blank">需求清单</a>
				</#if>
			</td>
		</tr>
		<tr>
			<td>需求概要：</td>
			<td>
				目的地：<#if AffairType.PLAN_A=curAffair.type>${planALocations['l_a_' + curAffair.packId]!''}<#elseif AffairType.PLAN_B=curAffair.type>${planBLocations['l_b_' + curAffair.advisorId]!''}</#if> <br/>
				出行时间：${curAffair.goDate} <br/>
				出行人数：${curAffair.adultNum}成人，${curAffair.childNum}儿童 
			</td>
			<td>
				<#if !readonly>
				<a class="btn btn-info btn-xs" onclick="javascript: sales.updateSalesAffairBasicInfo(this);">保存修改</a>
				</#if>
			</td>
			<td>
				<#if !hasLost && OrderStatus.TERMINATED.value=curAffair.orderStatus && Team.ADMIN.value=salesUser.team>
            	<a class="btn btn-warning btn-xs" class="btn btn-xs btn-warning" onclick="javascript: sales.updateSalesAffairOpen();">打开事务</a>
            	</#if>
            	<#if !readonly>
				<label><a href="http://www.shijiebang.com/supers/" target="_blank">A计划ID：</a><input class="form-control" type="text" id="smsPackId" value="${(curAffair.type==AffairType.PLAN_A && curAffair.packId gt 0)?string(curAffair.packId, '')}" ${(curAffair.type==AffairType.PLAN_A)?string('disabled', '')} size="8"></label>
				<a class="btn btn-warning btn-xs" onclick="javascript: sales.sendSms(this);">发送推荐短信</a>
				</#if>
				<span class="js-smslist-span text-info glyphicon glyphicon-info-sign" data-affair-id="${curAffair.id}">推荐记录</span>
			</td>
		</tr>
	</table>
  </div>
</div>

<div class="panel panel-info panel-yunhu panel-opportunity">
  <div class="panel-heading">
    <strong>跟单进展</strong>
    <#assign dayenBtn=(curAffair?? && curAffair.type=AffairType.PLAN_B && taskDoyenUser??) 
    					|| (curAffair?? && curAffair.type=AffairType.PLAN_A && orderIdList?? && orderIdList?size>0 && tripDoyenUsers[orderIdList[0]].uid??)>
	<#assign groupBth=(dayenBtn && salesUser.id=curOpp.salesId)>
	<#if groupBth>
    <a class="btn btn-warning btn-xs webim-groupchat-link" data-taskid="<#if curAffair.type=AffairType.PLAN_B>${task.taskId}<#else>${planATaskId}</#if>">三方同时在线沟通</a>
	</#if>
    <a class="btn btn-warning btn-xs webim-online-click" href="javascript:;"  data-id="${curOpp.uid}">与用户在线沟通</a>
    <#if dayenBtn>
    <a class="btn btn-warning btn-xs webim-online-click" href="javascript:;" data-id="<#if curAffair.type=AffairType.PLAN_B>${taskDoyenUser.uid}<#else>${tripDoyenUsers[orderIdList[0]].uid}</#if>">与达人在线沟通</a>
    </#if>
    <a class="btn btn-success btn-xs" href="http://www.shijiebang.com/u${curOpp.uid}/route/?saleId=${salesUser.getId()}&signature=${Util.getRouteUrlSignature(salesUser.getId(), curOpp.uid)}" target="_blank">查看用户流程节点</a>
    <#if curAffair.tid gt 0>
        <#assign quoteCustomerName=nick>
        <#if clueTaskInfo.name?? && clueTaskInfo.name != ''>
            <#assign quoteCustomerName=clueTaskInfo.name>
        </#if>
        <a class="btn btn-warning btn-xs" href="http://tmall.shijiebang.com/quote/route/saleEdit?aid=${curAffair.id}&atype=${curAffair.type.value}&tid=${curAffair.tid}&uid=${curOpp.uid}&name=${quoteCustomerName?url('utf-8')}<#if curAffair.type=AffairType.PLAN_A>&sid=${curAffair.packId}</#if>" target="_blank">提交报价申请</a>
    </#if>
    <a class="btn btn-success btn-xs" href="/user/order/?uid=${curOpp.uid}" target="_blank">查看用户订单</a>
  </div>
  <div class="panel-body">
    <#if AffairType.PLAN_B=curAffair.type>
    <table class="table table-striped table-bordered table-condensed vcenter form-inline table-yunhu text-center">
        <thead>
            <tr style="background:#efefef">
                <th>B计划任务ID</th>
                <th>任务发布时间</th>
                <th>旅行方案制作进度</th>
                <th>方案制作达人</th>
                <th>方案查看次数</th>
                <th>旅行方案</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <#if task?? >
                        ${task.taskId}
                    <#else>
                        <#if !readonly>
                            <a href="/advisor/${curAffair.advisorId}/" target="_blank" class="link">去发布小帮手任务</a>
                        <#else>
                            <a class="link" disabled="disabled">去发布小帮手任务</a>
                        </#if>
                    </#if>
                </td>
                <td>
                    <#if task?? && !(taskSession??)>
                        ${task.createdAt?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}
                    <#else>
                        --
                    </#if>
                </td>
                <td>
                    <#if task?? && taskSession?? && !(tripShell??)>
                        <#if taskSession.status?? && (taskSession.status='TaskDropped' || taskSession.status='TaskTerminated')>
                            已终止
                        <#else>
                            方案制作中
                        </#if>
                    <#elseif task?? && tripShell??>
                        <@tripShellStatus tripShell.status.value />
                    <#else>
                        --
                    </#if>
                </td>
                <td>
                    <#if taskSession??>
                        <a href="http://www.shijiebang.com/u${taskDoyenUser.uid!''}/" target="_blank" class="link">${taskDoyenUser.nick!''}</a>
                    <#else>
                        --
                    </#if>
                </td>
                <td>${curAffair.tripShellViewCount}</td>
                <td>
                    <#if taskSession??>
                        <#if (taskSession.tid>0)>
                            <a href="http://www.shijiebang.com/tripshell/${taskSession.tid}/" target="_blank" class="link">查看</a>
                        </#if>
                    <#else>
                        --
                    </#if>
                </td>
            </tr>
        </tbody>
    </table>
    </#if>

    <#if quoteIds??>
    <table class="table table-striped table-bordered table-condensed vcenter form-inline table-yunhu text-center">
    	<thead>
    		<tr class="success">
    			<th>已报价行程ID</th>
                <th>报价提交时间</th>
                <th>报价过期时间</th>
                <th>报价状态</th>
                <th>操作</th>
    		</tr>
    	</thead>
    	<tbody>
    		<#--A计划：标杆行程ID+UID到报价列表页，B计划：壳ID+UID到报价列表页-->
    		<#list quoteIds as qid>
    		   <tr>
    			  <#if (quoteInfos[qid+''])??>
    			  <td>${quoteInfos[qid+'']['tripId']}</td>
    			  <td>${(quoteInfos[qid+'']['createTime']*1000)?number_to_datetime}</td>
    			  <td><#if quoteInfos[qid+'']['expireTime'] gt 0>${(quoteInfos[qid+'']['expireTime']*1000)?number_to_datetime}</#if></td>
    			  <td>${quoteInfos[qid+'']['statusDesc']!''}</td>
    			  <td><a href="http://tmall.shijiebang.com/quote/route/quoteDetail?atype=${curAffair.type.value}&qid=${qid}" target="_blank">查看</a></td>
    			  </#if>
    		   </tr>
    		   </#list>
        </tbody>
    </table>
    </#if>

    <table class="table table-striped table-bordered table-condensed vcenter form-inline table-yunhu text-center">
    	<thead>
    		<tr class="success">
    			<th>已关联订单</th>
    			<th>操作</th>
    			<th>联系人</th>
    			<th>联系人手机</th>
    			<th>人员构成</th>
    			<th>出发城市</th>
    			<th>订单金额（元）</th>
    			<th>下单时间</th>
    		</tr>
    	</thead>
    	<tbody>
    	<#if !readonly>
    		<tr>
    			<td>
    				<input type="text" class="form-control input-sm" value="${refOrderId}" placeholder="输入订单号进行关联">
    			</td>
    			<td style="text-align:left">
    				<a class="btn btn-info btn-xs" onclick="javascript: sales.addAffairOrder(this);">关联订单</a>
    				<a href="/trip/relate/user_trips/?${(curAffair.type=AffairType.PLAN_A)?string('a', 'b')}_uid=${curOpp.uid}" class="link" target="_blank">关联单品</a>
    			</td>
    			<td></td>
    			<td></td>
    			<td></td>
    			<td></td>
    			<td></td>
    			<td></td>
    		</tr>
    	</#if>
    	<#list orderIdList as orderId>
    		<tr>
    			<td>
    				<a class="link" href="http://tmall.shijiebang.com/order/userorder/detail?mid=${orderId}" target="_blank">${orderId}</a>
    			</td>
    			<td style="text-align:left">
    				<#if !readonly>
    					<a class="link" onclick="javascript: sales.cancelAffairOrder('${orderId}');">取消关联</a>
    				<#else>
    					--
    				</#if>
    			</td>
            	<td>
					${orderInfo['o_'+orderId]['contact']['lastname_cn']}${orderInfo['o_'+orderId]['contact']['firstname_cn']}
				</td>
    			<td>
    				${orderInfo['o_'+orderId]['contact']['mobile']}
    			</td>
    				
    			<td>${orderInfo['o_'+orderId]['trip_info']['person_set']}</td>
    			<td>${orderInfo['o_'+orderId]['trip_info']['start_city']}</td>
    			<td>${(orderInfo['o_'+orderId]['price_total']!0)?string(',###.00')}</td>
    			<td><#if orderInfo['o_'+orderId]['time']['add_time']??>${(orderInfo['o_'+orderId]['time']['add_time']?number*1000)?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
    		</tr>
    	</#list>
    	</tbody>
    </table>
  </div>
</div>

<#if orderIdList?? && orderIdList?size gt 0>
<div class="panel panel-info panel-yunhu">
  <div class="panel-heading">
    <strong>付款后信息</strong>
  </div>
  <div class="panel-body">

    <table class="table table-striped table-bordered table-condensed vcenter text-center table-yunhu">
    	<thead>
    		<tr class="success">
    			<th>付款订单号</th>
    			<th>付款状态</th>
    			<th>付款时间</th>
    			<th>预定状态</th>
    			<th>详细行程状态</th>
    			<th>用户手册寄送</th>
    			<th>快递单号</th>
    		</tr>
    	</thead>
    	<tbody>
    		<#list orderIdList as orderId>
    		<tr>
    			<td>
    				${orderInfo['o_'+orderId]['order_id']}
    			</td>
    			<td>
    				<#if orderInfo['o_'+orderId]['time']['pay_time']!='0'>
    					已付全款
    				<#elseif orderInfo['o_'+orderId]['time']['deposit_time']!='0'>
    					已付定金
    				<#else>
    					未付款
    				</#if>
    			</td>
    			<td>
    				<#if orderInfo['o_'+orderId]['time']['pay_time']!='0'>
    					<#if orderInfo['o_'+orderId]['time']['pay_time']?? && orderInfo['o_'+orderId]['time']['pay_time']!='0'>
    						${(orderInfo['o_'+orderId]['time']['pay_time']?number*1000)?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}
    					</#if>
    				<#elseif orderInfo['o_'+orderId]['time']['deposit_time']!='0'>
    					${(orderInfo['o_'+orderId]['time']['deposit_time']?number*1000)?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}
    				<#else>
    					--
    				</#if>
    			</td>
    			<td>
    				<#if orderInfo['o_'+orderId]['is_complete']=1>
    				预定完成，
    				<br/>时间：${(orderInfo['o_'+orderId]['complete_time']*1000)?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}<br/>
    				<#if curAffair.type=AffairType.PLAN_B>
    				<a href="http://www.shijiebang.com/u${tripReview.uid}/trip-${tripReview.tid}/order/#ac_trip-nav" target="_blank">代订产品清单</a><br>
    				<#else>
    				<a href="http://www.shijiebang.com/u${tripReviews[orderId].uid}/trip-${tripReviews[orderId].tid}/order/#ac_trip-nav" target="_blank">代订产品清单</a><br>
    				</#if>
    				<#elseif orderInfo['o_'+orderId]['order_sub_list']?? && (orderInfo['o_'+orderId]['order_sub_list']?size>0)>
    				预定中<br>
    				<#else>
    				未开始预定<br>
    				</#if>
    			</td>
    			<td>
    				<#if curAffair.type=AffairType.PLAN_B>
    					<#if tripReview??>
    				
    						<@tripStatus tripReview.status.value!'' />
    						<a href="http://www.shijiebang.com/u${tripReview.uid}/trip-${tripReview.tid}/" target="_blank">查看</a><br/>
    						<a href="http://www.shijiebang.com/u${tripDoyenUser.uid!''}/" target="_blank">达人：${tripDoyenUser.nick!''}</a>
    					</#if>
    				<#else>
    					<#if tripReviews[orderId].status??><@tripStatus tripReviews[orderId].status.value!'' /></#if>
    					<a href="http://www.shijiebang.com/u${tripReviews[orderId].uid}/trip-${tripReviews[orderId].tid}/" target="_blank">查看</a><br/>
    					<a href="http://www.shijiebang.com/u${tripDoyenUsers[orderId].uid!''}/" target="_blank">达人：${tripDoyenUsers[orderId].nick!''}</a><br>
    					
    				</#if>
    			</td>
    			<td>
    			
    				<#if orderInfo['o_'+orderId]['post_status']??>
    					${OrderInfoMapper.getPostStatusInfo(orderInfo['o_'+orderId]['post_status']!'0'?number)}
    				</#if>
    			</td>
    					
    			<td>
    				<#if orderInfo['o_'+orderId]['post_status']=2>
    					${orderInfo['o_'+orderId]['post_no']}
    				<#else>
    					--
    				</#if>
    			</td>
       		</tr>
    		</#list>
    	</tbody>
    </table>
  </div>
</div>
</#if>
</#if>

<div class="panel panel-info panel-yunhu">
  <div class="panel-heading">
    <strong>事务任务信息</strong>
    <#if affairList?? && affairList?size gt 0>
    <a class="btn btn-default btn-warning btn-xs js-collapse-btn" data-toggle="collapse" href="#comment-add-box" aria-expanded="false" aria-controls="comment-add-box">处理任务</a>
  	</#if>
  </div>
  <div class="panel-body">
    <table class="table table-striped table-bordered table-condensed vcenter text-center table-yunhu js-affair-box">
    	<thead>
    		<tr class="success">
    			<th>事务名称</th>
    			<th>跟单阶段</th>
    			<th>任务分类</th>
    			<th>任务产生时间</th>
    			<th>期望完成时间</th>
    			<th>超时时长</th>
    			<th><label ><input class="js-check-all" type="checkbox">是否处理</label></th>
    		</tr>
    	</thead>
    	<tbody>
    		<#list workList as w>
    		<tr>
    			<td>${(affairMap[w.affairId+''].name)!''}</td>
    			<td>${(w.orderStatus.desc)!''}</td>
    			<td>${(w.type.desc)!''}<#if SalesWorkType.MANUAL=w.type> <i class="icon-hand-left"></i></#if></td>
    			<td>${w.createdAt?datetime}</td>
    			<td>${w.timeoutAt?datetime}</td>
    			<td><#if workTimeouts[''+w.workId] gt 0>${DateTimeUtils.longToInterval(workTimeouts[''+w.workId]*60*1000)}</#if></td>
    			<td><label><input type="checkbox" id="w_${w.workId}" data-workid="${w.workId}"> 处理</label></td>
       		</tr>
    		</#list>
    	</tbody>
    </table>

    <table class="table table-striped table-bordered table-condensed vcenter form-inline table-yunhu collapse" id="comment-add-box">
        <tr class="bg-success">
            <td style="width: 15%">
                方式：
                <select class="form-control" id="outcallType">
                <#list OutcallDetailType.values() as t>
                    <option value="${t.value}">${t.desc}</option>
                </#list>
                </select>
            </td>
            <td colspan="2">
                备注分类：
                <select class="form-control" id="outcallCategory" onchange="javascript: sales.changeOutcallCategory(this);">
                <#list OutcallDetailCategory.values() as c>
                	<#if !OutcallDetailCategory.isAfterDealt(c.value) || (anyAffairAfterDealt?? && anyAffairAfterDealt)>
                    <option value="${c.value}">${c.desc}</option>
                    </#if>
                </#list>
                </select>
                遇到的问题：
                <select class="form-control" id="outcallConclusion" onchange="javascript: sales.changeOppConclusion(this);">
                    <option value="">请选择</option>
                    <#list ptypes as p>
                    <option value="${p.val}">${p.name}</option>
                    </#list>
                </select>
                -
                <select class="form-control" id="outcallConclusionValue">
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <label><input type="checkbox" id="outcallNextRemind">手工设置任务</label>
            </td>
            <td style="width: 35%">    
                <label>手工设置的任务时间：<input type="text" class="form-control" id="outcallNextTime" data-date-format="yyyy-mm-dd hh:ii:ss" value="" size="20"></label>
            </td>
            <td>
                <label for="outcallComment">备注详情：</label>
                <textarea id="outcallComment" class="form-control" cols="80" rows="2"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
           		<input type="checkbox" id="sendSmsToCustomer" name="sendSmsToCustomer" ${(smsContent?? && smsContent != '')?string('', 'disabled="disabled"')}>
                <label for="sendSmsToCustomer">给用户发未通短信：</label>
                <textarea class="form-control" cols="60" rows="3" style="width:400px; height:50px;" disabled="disabled">${smsContent!''}</textarea>
            </td>
            <td>
                <button class="btn btn-info btn-xs" onclick="javascript: sales.addTaksInfoDetail(this, ${(readonly?? && readonly)?string('\'remarkothers\'', '\'opportunity\'')});">提交</button>
            </td>
        </tr>
    </table>
  </div>
</div>

<div class="panel panel-info panel-yunhu">
  <div class="panel-heading">
    <strong>任务处理信息（备注）</strong>
    
  </div>
  <div class="panel-body">

    <table class="table table-striped table-bordered table-condensed vcenter text-center table-yunhu">
    	<thead>
          <tr class="success">
			<th>备注人</th>
			<th>备注时间</th>
			<th>方式</th>
			<th>备注分类</th>
			<th>遇到的问题</th>
			<th>手工设置任务</th>
			<th>手工设置的任务时间</th>
			<th class="col-sm-4">外呼详情</th>
          </tr>
		</thead>
		<tbody class="text-center">
			<#if outcallDetailList?? && outcallDetailList?size gt 0>
			<#list outcallDetailList as detail>
			<tr>
				<td>${(salesUsers['u'+detail.salesId].name)!''}</td>
				<td>${detail.createdAt?datetime?string('yyyy-MM-dd HH:mm:ss')}</td>
				<td><#if detail.type??>${detail.type.desc}</#if></td>
				<td><#if detail.category??>${detail.category.desc}</#if></td>
				<td>
	    			<#if detail.conclusion gt 0>${conclusions[detail.conclusion+''].name!''}</#if>
	    			-
	    			<#if detail.conclusionValue gt 0>${conclusions[detail.conclusionValue+''].name!''}</#if>
    			</td>
				<td>${(detail.nextRemind==1)?string('是', '否')}</td>
				<td>
    				<#if (detail.nextRemind==1 && detail.nextTime??)>${detail.nextTime?datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>
    			</td>
    			<td>${detail.comment?html}</td>
			</tr>
			</#list>
			<#else>
			<tr><td colspan="8" class="text-center text-danger">无商机外呼记录</td></tr>
			</#if>
		</tbody>
    </table>
  </div>
</div>

<div class="panel panel-info panel-yunhu">
  <div class="panel-heading">
    <strong>线索外呼备注信息</strong>
  </div>
  <#assign conclusions=clueConclusions>
  <#include "sales/clue_detail_remark_content.ftl">
</div>
<script>
$(function(){
    sales.syncTasksCheckbox($('#outcallCategory').val());
    var collapseBtn = $(".js-collapse-btn"); 
    $(".js-affair-box input").click(function(){
        if(collapseBtn.attr("aria-expanded")=="false"){
            collapseBtn.click();
        }
    });
    $('.webim-groupchat-link').on("click", function(e){
        var me = $(this)
        ,   chatid = me.attr("data-chatid")
        ;
        if(!chatid){
            $.ajax({
                url:'/sales/affair/chat/',
                type:'get',
                dataType:"json",
                data:{taskId:me.data('taskid')},
                success:function(resp){
                    if(!resp.code){
                    	if (resp.data.chatId > 0) {
                    		me.attr('data-chatid', resp.data.chatId);
                        	me.trigger('click');
                    	} else {
                    		alert('出错了，无对应群组');
                    	}
                    }else{
                        alert('出错了，请稍后再试');
                    }
                }
            });
            e.preventDefault();
            return false;
        } 
        return true;
    });  
    $('.js-check-all').change(function(){
        var checked = $(this).is(":checked");
        if(checked){
           setAffairInp(true);
        }else{
           setAffairInp(false);
        }
    });
    var affairInps = $(".js-affair-box input"); 
    function setAffairInp (value){
        affairInps.each(function(index, item){
            item = $(item);
            if(!item.attr("disabled")){
                item.attr("checked",value)
            }
        });
    }
    function syncLostStatus (){
        var me = $("#orderStatus");
        if(me.val()=='7'){
            $(".js-orderStatus-target").removeClass('invisible');
        }else{
            $(".js-orderStatus-target").addClass('invisible');
        }
    }
    syncLostStatus();
    $("#orderStatus").change(syncLostStatus);
	$('.js-smslist-span').tooltip({
		title: sales.getAffairSmsList,
		html: true,
		template: '<div class="tooltip"><div class="tooltip-inner" style="width:300px;text-align:center;"></div></div>',
        placement:'bottom'
	});
});
</script>
