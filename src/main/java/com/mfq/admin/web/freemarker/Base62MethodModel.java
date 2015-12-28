package com.mfq.admin.web.freemarker;

import java.util.List;

import com.mfq.admin.web.utils.Base62;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class Base62MethodModel implements TemplateMethodModel {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        Long qid= Long.parseLong((String) arguments.get(0));

        return new SimpleScalar(Base62.encode(qid));

    }
}