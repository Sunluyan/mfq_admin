(function(exports) {
	exports.salesUsers = [];
	exports.conclusions = {};
	exports.promotions = {};
	exports.taskInfoId = 0;
	exports.opportunityId = 0;
	exports.affairId = 0;
	exports.searchType = '';
	exports.queryCond = '';
	exports.isSales = false;
	exports.salesId = 0;
	exports.callHistoryCache = {};

	exports.formatCallHistoryTooltip = function (entries, updateTooltip) {
		var tooltip = $('.tooltip .tooltip-inner').first();
		var tooltipHtml = '';
		if (!Array.isArray(entries)) {
			tooltipHtml = entries;
		}
		else if (entries.length === 0) {
			tooltipHtml = '没有外呼记录';
		}
		else {
			var htmlTemplate = $('.mobile-tooltip-content');
			htmlTemplate.find('.mobile-tooltip-count').html(entries.length);

			var tbody = htmlTemplate.find('tbody');
			var tr = tbody.find('tr').first().clone();
			tbody.empty();
			entries.slice(0, 4).forEach(function (entry) {
				tr.find('.mobile-tooltip-create').html(entry.createdAt);
				tr.find('.mobile-tooltip-source').html(entry.sourceName);
				tr.find('.mobile-tooltip-sales').html(entry.salesName);
				tr.find('.mobile-tooltip-conclusion').html(entry.latestConclusion > 0 ? '已拨打' : '未拨打');
				tbody.append(tr);
				tr = tr.clone();
			});
			tooltipHtml = htmlTemplate.html();
		}

		if (updateTooltip) {
			tooltip.html(tooltipHtml);
		}
		else {
			return tooltipHtml;
		}
	};

	exports.requestCallHistoryEntries = function () {
		var CALL_HISTORY_CACHE_TTL = 60*1000;
		var mobile = $(this).text();
		if ((mobile in exports.callHistoryCache) && (Date.now() - exports.callHistoryCache[mobile].timestamp) < CALL_HISTORY_CACHE_TTL) {
			return exports.formatCallHistoryTooltip(exports.callHistoryCache[mobile].entries, false);
		}

		$.ajax({
			url: '/sales/clue/mobile/',
			async: true,
			data: {mobile: mobile},
			type: "GET",
			dataType: "json",
			success: function(res) {
				if (res.code == 0 && res.data.length > 0) {
					exports.callHistoryCache[mobile] = {
						timestamp: Date.now(),
						entries: res.data
					};
					exports.formatCallHistoryTooltip(res.data, true);
				} else {
					console.error(res.code + ':' + res.msg);
					exports.formatCallHistoryTooltip('获取记录失败('+res.code+'): '+res.msg, true);
				}
			},
			error: function(xhr, status, error) {
				console.error(error);
				exports.formatCallHistoryTooltip('获取记录失败('+status+'):'+error, true);
			}
		});

		return $('.mobile-tooltip-loading').html();
	};

	exports.getMobileHistory = function() {
	  	var his = '';
	  	var mobile = $(this).text();
	  	$.ajax({
			url: '/sales/clue/mobile/',
			async: false,
			data: {mobile: mobile},
			type: "GET",
			dataType: "json",
			success: function(res) {
		  		if (res.code == 0) {
                    //限制5条，时间新到旧排列
                    var dataArray = res.data.length > 5 ? res.data.slice(res.data.length-5) : res.data;
                    his ='<div class="tip-list-group">'
					for (var i = dataArray.length-1; i >=0 ; i--) {
                        his += '<p class="tip-list-item">'
						his += '留电话时间：' + dataArray[i].createdAt + '<br/>';
						his += '负责人：' + dataArray[i].salesName  
					    his += '&nbsp;|&nbsp;拨打情况：' + (dataArray[i].latestConclusion > 0 ? '已拨打' : '未拨打 ') + '<br/>';
						his += '来源页：' + dataArray[i].sourceName ;
                        his += '</p>'
					}
                    his += '</div>';
		  		} else {
		    		his = '获取记录失败';
		  		}
			},
			error: function() {
				his = '获取记录失败';
			}
	  	});
	  	return his;
	};

	exports.addTaskInfo = function(btn) {
		var sourceKey = $("#addFormSourceKey").val();
		var mobile = $.trim($("#addFormMobile").val());
		var expectedTime = $.trim($("#addFormExceptedTime").val());
		if (sourceKey == '') {
			alert("请输入活动");
			return false;
		}
		if (!/\d{6,}/.test(mobile) || mobile.length > 32) {
			alert("请输入正确的手机号");
			return false;
		}
		$(btn).attr("disabled", "disabled");
		$.ajax({
			url: '/sales/remind/clue/new/',
			data: {sourceKey: sourceKey, mobile: mobile, expectedTime: expectedTime},
			type: "POST",
			dataType: "json",
			success: function(res) {
				if (res.code == 0) {
					alert('新增成功');
					$("#search-form").submit();
				} else {
					alert('新增失败: ' + res.msg);
					$(btn).removeAttr("disabled");
					return false;
				}
			},
			error: function() {
				alert('新增失败');
				$(btn).removeAttr("disabled");
				return false;
			}
		});
	};
	
	exports.queryUserByMobile = function() {
		var mobile = $.trim($("#addFormMobile").val());
		if ('' == mobile) {
			return false;
		}
		$.ajax({
			url: '/sales/clue/getuser/',
			data: {mobile: mobile},
			type: "GET",
			dataType: "json",
			success: function(res) {
				if (res.code == 0) {
					if (res.data && res.data.uid != '' && res.data.uid != '0') {
						$('#addFormUid').val(res.data.uid);
					}
				} else {
					return false;
				}
			},
			error: function() {
				return false;
			}
		});
	};

	exports.updateRemindStatus = function(btn, remindId) {
		var btn = $(btn);
		if (!confirm('确认已提醒？')) {
			return false;
		}

		btn.attr("disabled", "disabled");
		$.ajax({
      		url: '/sales/remind/status/',
      		data: {remindId: remindId},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			alert('修改成功');
          			btn.removeAttr("onclick");
        		} else {
          			alert('修改失败: ' + res.msg);
          			btn.removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert('修改失败');
        		btn.removeAttr("disabled");
        		return false;
      		}
    	});
	};

	exports.toModifyOpportunitySales = function(assist, btn) {
		var salesUsers = this.salesUsers;
		var btn = $(btn);
		var sel = $('<select></select>');
		if (1 == assist) {
			sel.append($('<option value="">请选择</option>'));
		}
		for (var i = 0; i < salesUsers.length; i++) {
			sel.append($('<option value="' + salesUsers[i].id + '">' + salesUsers[i].name + '</option>'));
		}
		sel.insertBefore(btn);
		btn.hide();
		btn.siblings("button").show();
	};

	exports.modifyOpportunitySales = function(oppId, assist, btn) {
		var btn = $(btn);
		var salesId = btn.siblings("select").val();
		if (!confirm('确定修改?')) {
			return false;
		}
		btn.attr("disabled", "disabled");
		$.ajax({
      		url: '/sales/admin/opportunity/sales/',
      		data: {oid: oppId, salesId: salesId, isAssist: assist},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			alert('修改成功');
          			window.location.href='/sales/admin/affair/searchall/';
        		} else {
          			alert('修改失败: ' + res.msg);
          			btn.removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert('修改失败');
        		btn.removeAttr("disabled");
        		return false;
      		}
    	});
	};

	exports.addTaksInfoDetail = function(btn, detailType) {
        var affairInp = $(".js-affair-box input")
        ,   workIds = [];
        ;
        
        if(affairInp.length>0){
            var affairCheckedInp = $(".js-affair-box input:checked");
            if(!affairCheckedInp.length){
                alert('请选择您要处理的任务');
                return false;
            }
            affairCheckedInp.each(function(index){
                workIds.push($(this).data('workid'));
            });
        }
        

		// 外呼备注信息
		var taskInfoId = this.taskInfoId;
		var opportunityId = this.opportunityId;
		var affairId = this.affairId;
		var searchType = this.searchType;
		var queryCond = this.queryCond;
		var isSales = this.isSales;
		var oppSalesId = $('#oppSalesId')[0] ? $('#oppSalesId').val() : 0;
		var type = $('#outcallType').val() || 0;
		var category = $('#outcallCategory').val() || 0;
		var conclusion = $('#outcallConclusion').val();
		var conclusionValue = $('#outcallConclusionValue').val();
		var comment = $.trim($('#outcallComment').val());
		var nextRemind = $('#outcallNextRemind').attr("checked") ? 1 : 0;
		var nextTime = $.trim($('#outcallNextTime').val());
		var sendSmsToCustomer = $('#sendSmsToCustomer').attr("checked") ? 1 : 0;
		// 线索用户信息
		var name = $("#name").val();
		var mobile1 = $("#backupMobile1").val();
		var mobile2 = $("#backupMobile2").val();
		var promotion = $("#promotion").val();
		var promotionValue = $("#promotionValue").val() || 0;

		var url = '';
		var refreshUrl = '';
		if ('clue' == detailType) {
			url = '/sales/clue/outcall/';
			refreshUrl = '/sales/remind/clue/search/';
		} else if ('opportunity' == detailType) {
			url = '/sales/opportunity/outcall/';
			refreshUrl = '/sales/opportunity/detail/?oid=' + opportunityId + '&aid=' + affairId;
		} else if ('remarkothers' == detailType) {
			url = '/sales/opportunity/remarkothers/';
			refreshUrl ='/sales/opportunity/searchothers/?t=' + sales.searchType + '&q=' + sales.queryCond + '&oid=' + opportunityId + '&aid=' + affairId;
		}

		if (comment == '') {
			alert("请填写外呼详情");
			return false;
		}

		$(btn).attr("disabled", "disabled");
		$.ajax({
      		url: url,
      		data: {taskInfoId: taskInfoId, aid: affairId, oppSalesId: oppSalesId, type: type, workIds: workIds.join(','), 
      				category: category, conclusion: conclusion, conclusionValue: conclusionValue, comment: comment, 
      				nextRemind: nextRemind, nextTime: nextTime, sendSmsToCustomer: sendSmsToCustomer, 
      				name: name, backupMobile1: mobile1, backupMobile2: mobile2, promotion: promotion, promotionValue: promotionValue},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			alert('保存备注成功');
          			if (isSales && res.data && res.data.oppCreatedId && res.data.oppCreatedId > 0) {
          				refreshUrl = '/sales/opportunity/detail/?oid=' + res.data.oppCreatedId;
          			}
          			window.location.href = refreshUrl;
        		} else {
        			if (res.data && res.data.oppIdWithoutAffair && isSales) {
        				if (confirm('保存备注失败: ' + res.msg)) {
        					window.location.href = '/sales/opportunity/detail/?oid=' + res.data.oppIdWithoutAffair;
        				}
        			} else {
        				alert('保存备注失败: ' + res.msg);
        			}
          			$(btn).removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert('保存备注失败');
        		$(btn).removeAttr("disabled");
        		return false;
      		}
    	});
	};

	exports.updateTypeValue = function(btn, val, type) {
		var name = $.trim($(btn).siblings("input[type=text]").val());
		var proctype = $.trim($(btn).siblings("select[name='proctype_" + val + "']").val());
		var sms = $.trim($(btn).siblings("input[name='sms_"+ val + "']:checked").val());
		var status = $(btn).siblings("input[type=checkbox]").attr("checked") == "checked" ? 1 : 0;
		$(btn).attr("disabled", "disabled");
		$.ajax({
      		url: '/sales/admin/setting/typevalue/',
      		data: {val: val, name: name, s:status, proctype: proctype, sms: sms},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			alert('修改成功');
          			window.location.href = '/sales/admin/setting/typevalue/list/' + (('1'== type || '' == type) ? '' : '?t=' + type);
        		} else {
          			alert('修改失败: ' + res.msg);
          			$(btn).removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert('修改失败');
        		$(btn).removeAttr("disabled");
        		return false;
      		}
    	});
	};

	exports.addTypeValue = function(btn, type) {
		var val = $.trim($(btn).siblings("input[name='val']").val());
		var name = $.trim($(btn).siblings("input[name='name']").val());
		var proctype = $.trim($(btn).siblings("select[name='proctype']").val());
		var sms = $.trim($(btn).siblings("input[name='sms']:checked").val());
		var pvalue = $(btn).siblings("select").val() || 0;
		if (val  == '' || isNaN(val)) {
			alert("请输入正确的编码");
			return false;
		}
		if (name == '') {
			alert("请输入名称");
			return false;
		}
		$(btn).attr("disabled", "disabled");
		$.ajax({
      		url: '/sales/admin/setting/typevalue/new/',
      		data: {pvalue: pvalue, val: val, name: name, t: type, proctype: proctype, sms: sms},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			alert('新增成功');
          			window.location.href = '/sales/admin/setting/typevalue/list/' + (('1'== type || '' == type) ? '' : '?t=' + type);
        		} else {
          			alert('新增失败: ' + res.msg);
          			$(btn).removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert('新增失败');
        		$(btn).removeAttr("disabled");
        		return false;
      		}
    	});
	};

	exports.modifySourceTeam = function(sourceKey, btn) {
		if (!confirm('确认修改？')) {
			return false;
		}
		$(btn).attr("disabled", "disabled");
		$.ajax({
      		url: '/sales/admin/sourceteam/',
      		data: {sourceKey: sourceKey, team: $("#st_" + sourceKey).val()},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			alert('修改成功');
        			window.location.href = '/sales/admin/sourceteam/list/';
        		} else {
          			alert('修改失败');
          			$(btn).removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert('修改失败');
        		$(btn).removeAttr("disabled");
        		return false;
      		}
    	});
	};

	exports.changePromotion = function(sel) {
		this.changeSelect(sel, sales.promotions, "#promotionValue", true);
	};
	
	exports.changeOppConclusion = function(sel) {
		this.changeSelect(sel, sales.conclusions, $("#outcallConclusionValue")[0], false);
	};
	
	exports.changeClueSearchConclusion = function(sel) {
		this.changeSelect(sel, sales.conclusions, $(sel).siblings("select")[0], true);
	};

	/**
	 * 1st level select: #outcallConclusion        onChange = changeConclusionWithInfo()
	 * 2nd level select: #outcallConclusionValue   onChange = changeInfo()
	 *
	 */

	exports.changeConclusion = function() {
		this.changeInfo();
	};
	
	exports.changeConclusionWithInfo = function(sel) {
		this.changeSelect(sel, sales.conclusions, "#outcallConclusionValue", false);
		this.changeInfo();
	};
	
	exports.changeInfo = function() {
		$("#conclusionInfo").html("");
		if (!this.isSales) {
			$("#salesTr").hide();
		}
		var cval = $("#outcallConclusionValue").val();
		var $pSel = $("#outcallConclusion");
		var pval = $pSel.val();
		var clist = sales.conclusions["p"+pval];
        clist = clist ? clist:[];
		var isOpprel = false;
		var userSms = false;
		var smsContent = '';
		for (var i = 0; i < clist.length; i++) {
			if (clist[i].val == cval) {
				if (1 == clist[i].opprel) {
					$("#conclusionInfo").html("保存成功后，该线索将自动受理为商机");
					isOpprel = true;
				} else if (1 == clist[i].regrab) {
					$("#conclusionInfo").html("保存成功后，该线索将重新进入外呼分配队列");
				} else {
					$("#conclusionInfo").html("保存成功后，该线索将被移出外呼队列，不再进行外呼");
				}
				userSms = clist[i].userSms == 1;
				smsContent = clist[i].smsContent;
				break;
			}
		}
		var $smsDiv = $(".js-user-sms-content-div");
		if (userSms) {
			$smsDiv.append($('<label class="control-label col-sm-2"><input type="checkbox" id="sendSmsToCustomer" value="1" checked="checked">发送短信：</label><div>' + smsContent + '</div>'));
			$(".js-user-sms-content-div").show();
		} else {
			$smsDiv.children().remove();
		}
		if (!this.isSales && isOpprel) {
			$("#salesTr").show();
		}
	};
	
	exports.changeSelect = function(source, options, target, blankOpt) {
		var pid = $(source).val();
		var clist = options["p"+pid];
		$(target).empty();
		if (blankOpt) {
			$(target).append('<option value="">请选择</option>');
		}
		for (var i = 0; i < clist.length; i++) {
			$(target).append('<option value="' + clist[i].val + '">' + clist[i].name + '</option>');
		}
	};

	exports.updateTaskInfo = function(btn, clueTaskId) {
		var taskInfoId = clueTaskId || this.taskInfoId;
		var name = $("#name").val();
		var mobile1 = $("#backupMobile1").val();
		var mobile2 = $("#backupMobile2").val();
		var promotion = $("#promotion").val();
		var promotionValue = $("#promotionValue").val() || 0;

		$(btn).attr("disabled", "disabled");
		$.ajax({
      		url: '/sales/clue/detail/',
      		data: {taskInfoId: taskInfoId, name: name, backupMobile1: mobile1, backupMobile2: mobile2, promotion: promotion, promotionValue: promotionValue},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			alert('保存成功');
          			if (!clueTaskId) {
          				window.location.href='/sales/clue/detail/?taskInfoId=' + taskInfoId;
          			} else {
          				window.location.reload();
          			}
        		} else {
          			alert('保存失败: ' + res.msg);
          			$(btn).removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert('保存失败');
        		$(btn).removeAttr("disabled");
        		return false;
      		}
    	});
	};

	exports.createOpportunity = function(btn) {
		var taskInfoId = this.taskInfoId;
		var salesId = 0;
		if (!this.isSales) {
			if (!$("#salesDiv").is(":visible")) {
				$("#salesDiv").show();
				return;
			} else {
				salesId = $("#salesDiv").children("select").val();
				if (salesId == 0) {
					alert("请输入销售负责人 ");
				}
			}
		}
		$(btn).attr("disabled", "disabled");
		$.ajax({
      		url: '/sales/opportunity/new/',
      		data: {taskInfoId: taskInfoId, salesId: salesId},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			alert('创建成功');
          			window.location.href='/sales/clue/detail/?taskInfoId=' + taskInfoId;
        		} else {
          			alert('创建失败: ' + res.msg);
          			$(btn).removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert('创建失败');
        		$(btn).removeAttr("disabled");
        		return false;
      		}
    	});
	};

	exports.regUser = function(mobile, btn) {
		window.location.href = "/crm/user/register/?ref=sales&taskId=" + this.taskInfoId + "&mobile=" + mobile;
	};

	exports.createAdvisor = function(nick, uid, oid){
        if(confirm("确定帮用户" + nick + "代填小帮手吗？代填的小帮手，填写联系方式时，对方不会收到短信和邮箱提示。")){
             window.location.href = '/crm/advisor/?uid=' + uid + '&oid=' + oid;
        } else {
        	return false;
        }
    };

	exports.addOrUpdateSalesUser = function(btn) {
		var id = this.salesId;
		var username = $("#username").val();
		var workVol = $("#workVol").val();
		var team = $("#team").val();
		var subTeamName = $.trim($('#subTeamName').val());
		var sign = $("#sign").val();
        var cno = $("#cno").val();
		var tip = id > 0 ? '修改' : '新增';

		if (!username || !team || !subTeamName) {
			alert('用户名和部门分组必须填写！');
            return false;
		}

		$(btn).attr("disabled", "disabled");
		$.ajax({
      		url: id > 0 ? '/sales/admin/sales/basicinfo/': '/sales/admin/sales/new/',
      		data: {id: id, username: username, workVol: workVol, team: team, subTeamName: subTeamName, sign: sign,cno:cno},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			var salesId = id > 0 ? id : res.data;
          			alert(tip + '成功');
          			$(btn).removeAttr("disabled");
          			window.location.href='/sales/admin/sales/detail/?id=' + salesId;
        		} else {
          			alert(tip + '失败: ' + res.msg);
          			$(btn).removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert(tip + '失败');
        		$(btn).removeAttr("disabled");
        		return false;
      		}
    	});
	};

	exports.addAffairOrder = function(btn) {
		var opportunityId = this.opportunityId;
		var affairId = this.affairId;
		var btn = $(btn);
		var orderId = $.trim((btn.parent().siblings('td').children(':text').val()));

		if ('' == orderId) {
			alert('请输入订单号');
			return false;
		}
		if (!confirm('确认关联订单？')) {
			return false;
		}

		$(btn).attr("disabled", "disabled");
		$.ajax({
      		url: '/sales/affair/order/new/',
      		data: {"aid": affairId, "orderId": orderId},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			alert('关联订单成功');
          			$(btn).removeAttr("disabled");
          			window.location.href = '/sales/opportunity/detail/?oid=' + opportunityId + '&aid=' + affairId;
        		} else {
          			alert('关联订单失败: ' + res.msg);
          			$(btn).removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert('关联订单失败');
        		$(btn).removeAttr("disabled");
        		return false;
      		}
    	});
	};

	exports.cancelAffairOrder = function(orderId) {
		var opportunityId = this.opportunityId;
		var affairId = this.affairId;
		var btn = $(btn);

		if (!confirm('确认取消关联？')) {
			return false;
		}

		$(btn).attr("disabled", "disabled");
		$.ajax({
      		url: '/sales/affair/order/status/',
      		data: {"aid": affairId, "orderId": orderId},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			alert('取消关联成功');
          			$(btn).removeAttr("disabled");
          			window.location.href = '/sales/opportunity/detail/?oid=' + opportunityId + '&aid=' + affairId;
        		} else {
          			alert('取消关联失败: ' + res.msg);
          			$(btn).removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert('取消关联失败');
        		$(btn).removeAttr("disabled");
        		return false;
      		}
    	});
	};

	exports.updateSalesAffairBasicInfo = function(btn) {
		var opportunityId = this.opportunityId;
		var affairId = this.affairId;
		var name = $.trim($("#affairName").val());
		var orderStatus = $("#orderStatus").val();
		var orderRate = $("#orderRate").attr("checked") ? 5:3;
		var lostReason = $("#lostReason").val();

		if ('' == name) {
			alert('请输入事务名称');
			return false;
		}
		if ('7' == orderStatus && ('0' == lostReason || '' == lostReason)) {
			alert('请输入丢单原因');
			return false;
		}

		if (!confirm('确认修改？')) {
			return false;
		}

		$(btn).attr("disabled", "disabled");
		$.ajax({
      		url: '/sales/affair/detail/',
      		data: {'aid': affairId, 'name': name, 'orderStatus': orderStatus, 'orderRate': orderRate, 'lostReason': lostReason},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			alert('修改成功');
          			$(btn).removeAttr("disabled");
          			window.location.href = '/sales/opportunity/detail/?oid=' + opportunityId + '&aid=' + affairId;
        		} else {
          			alert('修改失败: ' + res.msg);
          			$(btn).removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert('修改失败');
        		$(btn).removeAttr("disabled");
        		return false;
      		}
    	});
	};
	
	exports.updateSalesAffairOpen = function(btn) {
		var opportunityId = this.opportunityId;
		var affairId = this.affairId;

		if (!confirm('确认打开事务？')) {
			return false;
		}

		$(btn).attr("disabled", "disabled");
		$.ajax({
      		url: '/sales/admin/affair/open/',
      		data: {'aid': affairId},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			alert('打开成功');
          			$(btn).removeAttr("disabled");
          			window.location.href = '/sales/opportunity/detail/?oid=' + opportunityId + '&aid=' + affairId;
        		} else {
          			alert('打开失败: ' + res.msg);
          			$(btn).removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert('打开失败');
        		$(btn).removeAttr("disabled");
        		return false;
      		}
    	});
	};
	
	exports.changeOutcallCategory = function(btn) {
		var category  = $('#outcallCategory').val();
        exports.syncTasksCheckbox(category);
	}
    
    exports.syncTasksCheckbox = function(category){
        var workIds = []
        ,   affairInp = $(".js-affair-box input")
        ;

        if(!affairInp.length){
            return false;
        }

        affairInp.each(function(index){
            workIds.push($(this).data('workid'));
        });

        $.ajax({
            url:'/sales/affair/workcategory/',
            data:{category:category, workIds:workIds.join(",") },
            dataType:'json',
            type:'get',
            success:function(resp){
                if(!resp.code){
                    affairInp.each(function(index){
                        var me = $(this)
                        ,   wid = me.data('workid')
                        ;
                        if((typeof resp.data['w_'+wid] != "undefined") && !resp.data['w_'+wid]){
                            me.attr("checked", false);
                            me.attr("disabled", true);
                        }else{
                            me.attr("disabled", false);
                        } 

                    });
                }
            }
        });
    }
    
	exports.sendSms = function(btn) {
		var opportunityId = this.opportunityId;
		var affairId = this.affairId;
		var packId  = $('#smsPackId').val();
		
		if (packId  == '' || isNaN(packId)) {
			alert("请输入正确的A计划ID");
			return false;
		}
			
		$(btn).attr("disabled", "disabled");
		$.ajax({
      		url: '/sales/affair/sms/',
      		data: {'aid': affairId, "packId": packId},
      		type: "POST",
      		dataType: "json",
      		success: function(res) {
        		if (res.code == 0) {
          			alert('发送成功');
          			$(btn).removeAttr("disabled");
          			window.location.href = '/sales/opportunity/detail/?oid=' + opportunityId + '&aid=' + affairId;
        		} else {
          			alert('发送失败: ' + res.msg);
          			$(btn).removeAttr("disabled");
          			return false;
        		}
      		},
      		error: function() {
        		alert('发送失败');
        		$(btn).removeAttr("disabled");
        		return false;
      		}
    	});
	}
	
	exports.getAffairSmsList = function() {
	  	var affairId = $(this).data('affair-id');
		var sms = '';
	  	$.ajax({
			url: '/sales/affair/smslist/',
			async: false,
			data: {aid: affairId},
			type: "GET",
			dataType: "json",
			success: function(res) {
		  		if (res.code == 0) {
                    var dataArr = res.data;
					if (res.data && dataArr.length > 0) {
	                    for (var i = 0; i <= dataArr.length-1; i++) {
	                        sms += '<p class="tip-list-item">';
	                        sms += 'A计划ID：' + dataArr[i].packId + '&nbsp';
	                        sms += '发送时间：' + new moment(dataArr[i].createdAt).format('YYYY-MM-DD HH:mm:ss');
	                        sms += '</p>';
						}
					} else {
						sms += '<p>无记录</p>';
					}
                    sms += '</div>';
		  		} else {
		  			sms = '获取记录失败';
		  		}
			},
			error: function() {
				sms = '获取记录失败';
			}
	  	});
	  	return sms;
	};
})(this.sales = {});

(function(){
    $(function(){
        //拨打400电话按钮
        $('body').on("click", ".call-400-label", function(){
            var me = $(this)
            ,   mobile = me.attr("data-phone-number")
            ,   taskInfoId = me.attr("data-task-info-id") 
            ;
            
            if(!mobile){
                return false;
            }

            $.ajax({
                url:'/sales/outcall/call/'+'?' + "taskInfoId="  + ( taskInfoId ? taskInfoId : 0 ) + "&mobile="+mobile,
                type:'post',
                dataType:'json',
                data:{
                    mobile:mobile,
                    taskInfoId:taskInfoId ? taskInfoId : 0
                },
                success:function(resp){
                    if(!resp.code){ 
                        $("#yunhu-alert-dialog").modal()
                            .find('.modal-body').html('<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>呼叫成功');
                    }else{
                        $("#yunhu-alert-dialog").modal()
                            .find('.modal-body').html('<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>呼叫失败，'+resp.msg);
                    }
                }
            });
        });

        //公共线索电话号码搜索功能
        var searchResult = $("div.js-clue-search-result")
        ,   searchInp = $("input.js-clue-search-inp")
        ,   searchResetBtn = $('.js-clue-search-reset')
        ,   clueList = $(".js-clue-search-list")
        ;
        
        function searchClue (){
            var mobile = searchInp.val();
            if(!mobile){
                return false;
            }

            var reg = /^1\d{10}$/;
            if(!reg.test(mobile)){
                searchResult.html('<div class="alert alert-danger">请输入正确的手机号</div>').show();
                switchSearch('show');
                return false;    
            }

            $.ajax({
                url:"/sales/outcall/searchtaskinfo/",
                type:"get",
                dataType:"json",
                data:{mobile:mobile},
                success:function(resp){
                    if(!resp.code){
                        if(!resp.data.length){
                            searchResult.html('<div class="alert alert-info">查询无结果，更换号码试试</div>').show();
                        }else{
                            searchResult.html(buildResult(resp.data));
                        }
                        switchSearch('show');
                    }else{
                        alert("错误："+resp.msg);
                    }
                }
            });
        }

        function switchSearch(type){
            if(type == "show" && !searchResetBtn.hasClass('hidden')){
                return false;
            }
            
            searchResetBtn.toggleClass("hidden");
            searchResult.toggleClass("hidden");
            clueList.toggleClass("hidden");

            if(searchResetBtn.hasClass('hidden')){
                searchInp.val('');
            }

        }

        function buildResult(data){
            var ret = '<table class="table table-striped table-bordered table-condensed vcenter text-center">';
            ret += '<thead><tr class="success"><th width="100px">留电话页</th><th width="50px">用户UID</th><th width="50px">手机号</th><th width="100px">期望外呼时间</th><th width="50px">操作</th></tr></thead><tbody>';
            data.forEach(function(item){
                ret += '<tr><td>'+item.taskName+'</td><td>' + item.uid + '</td><td>' + item.mobile + '</td><td>' + (item.expectedTime > 0 ? new moment(item.expectedTime).format('YYYY-MM-DD HH:mm:ss') : '') + '</td><td>' 
                		+ '<a class="js-grab-button btn btn-sm btn-primary" data-task-id="'+item.taskId+'" data-force="1">强抢</a>'+'</td></tr>'    
            });
            ret +='</tbody></table>';
            return ret;
        }
        
        $('.js-clue-search-btn').click(searchClue);
        searchInp.keyup(function(event){
            if(event.keyCode == 13){
                searchClue();
            }else{
                searchResult.html('');
            }
        });
        searchResetBtn.click(function(){
            switchSearch();
        });

    });
})();
