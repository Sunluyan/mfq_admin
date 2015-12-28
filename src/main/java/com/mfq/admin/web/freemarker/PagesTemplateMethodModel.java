package com.mfq.admin.web.freemarker;

import java.util.List;

import com.mfq.admin.web.utils.PageUtils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 通用分页第二版
 * @see PageUtils#buildPageUrl(int, String, String)
 */
public class PagesTemplateMethodModel implements TemplateMethodModel {

    @SuppressWarnings("rawtypes")
    @Override
    public Object exec(List args) throws TemplateModelException {
        // ${pages(page,uri,queryString)}
        int page = Integer.parseInt(args.get(0).toString());
        String uri = (String) args.get(1);
        String queryString = args.size() > 2 ? (String) args.get(2) : null;
        String url = PageUtils.buildPageUrl(page, uri, queryString);
        return new SimpleScalar(url);
    }

}
