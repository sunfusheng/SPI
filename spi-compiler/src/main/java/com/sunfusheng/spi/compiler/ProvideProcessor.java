package com.sunfusheng.spi.compiler;

import com.google.auto.service.AutoService;
import com.sunfusheng.spi.annotation.Provide;

import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * @author by sunfusheng on 2019/3/14
 */
@AutoService(Processor.class)
public class ProvideProcessor extends AbstractProcessor {

    // 文件相关的辅助类
    private Filer mFiler;

    // 元素相关的辅助类
    private Elements mElementUtils;

    // 日志相关的辅助类
    private Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
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

        printNote("### Found providers, size is " + elements.size());
        for (Element element : elements) {
            if (filterElement(element)) {
                continue;
            }
            TypeElement typeElement = (TypeElement) element;
            String className = typeElement.getQualifiedName().toString();
            String annotationValue = getProviderAnnotationValue(element);
            printNote("### className: " + className);
            printNote("### annotationValue: " + annotationValue);
        }
        return true;
    }

    // 过滤被注解的元素
    private boolean filterElement(Element element) {
        if (element != null && element.getKind() == ElementKind.CLASS && element.getAnnotation(Provide.class) != null) {
            return false;
        }
        return true;
    }

    // 获取注解值的全限定名
    private String getProviderAnnotationValue(Element element) {
        try {
            Provide provide = element.getAnnotation(Provide.class);
            Class<?> clazz = provide.value();
            return clazz.getCanonicalName();
        } catch (MirroredTypeException mte) {
            DeclaredType declaredType = (DeclaredType) mte.getTypeMirror();
            TypeElement typeElement = (TypeElement) declaredType.asElement();
            return typeElement.getQualifiedName().toString();
        }
    }

    // 输出提示信息
    private void printNote(String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));
    }

    // 输出错误信息
    private void printError(String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args));
    }
}
