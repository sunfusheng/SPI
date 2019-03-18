package com.sunfusheng.spi.compiler;

import com.google.auto.service.AutoService;
import com.sunfusheng.spi.annotation.Provide;

import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * @author by sunfusheng on 2019/3/14
 */
@AutoService(Processor.class)
public class ProvideProcessor extends AbstractProcessor {

    private ProvideCodeGenerator mCodeGenerator;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mCodeGenerator = new ProvideCodeGenerator(processingEnv);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Provide.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        if (annotations == null || annotations.isEmpty()) {
            return false;
        }

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Provide.class);
        if (elements == null || elements.isEmpty()) {
            return false;
        }

        mCodeGenerator.generateCode(elements);
        return true;
    }
}
