<div class="panel-body">
    <table class="table table-striped table-bordered table-condensed vcenter text-center table-yunhu">
		<thead>
            <tr class="bg-success">
			    <th class="col-sm-1">操作人</th>
			    <th class="col-sm-2">外呼时间</th>
			    <th class="col-sm-2">外呼结论</th>
			    <th class="col-sm-7">外呼详情</th>
            </tr>
		</thead>
		<tbody class="text-center">
			<#if clueOutcallDetailList?? && clueOutcallDetailList?size gt 0>
				<#list clueOutcallDetailList as detail>
                <tr>
                    <td>${(salesUsers['u'+detail.salesId].name)!('ID:'+detail.salesId)}</td>
                    <td>${detail.createdAt?datetime?string('yy-MM-dd HH:mm')}</td>
                    <td>
						<#if detail.conclusion gt 0>${conclusions[detail.conclusion+''].name!''}</#if>
                        -
						<#if detail.conclusionValue gt 0>${conclusions[detail.conclusionValue+''].name!''}</#if>
                    </td>
                    <td>${detail.comment?html}</td>
                </tr>
				</#list>
			<#else>
				<tr><td colspan="4" class="text-center text-danger">无线索外呼记录</td></tr>
			</#if>
		</tbody>
    </table>
</div>
