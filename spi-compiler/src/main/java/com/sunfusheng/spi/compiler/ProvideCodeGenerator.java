package com.sunfusheng.spi.compiler;

import com.google.common.base.Strings;

import java.util.UUID;

/**
 * @author by sunfusheng on 2019/3/18
 */
public class ProvideCodeGenerator {

    private static String getPublicPackageName(String pkgName1, String pkgName2) {
        if (Strings.isNullOrEmpty(pkgName1) && Strings.isNullOrEmpty(pkgName2)) {
            return UUID.randomUUID().toString()
                    .replaceAll("[^0-9a-zA-Z]+", "")
                    .toUpperCase();
        }
        if (Strings.isNullOrEmpty(pkgName1)) {
            return pkgName2;
        } else if (Strings.isNullOrEmpty(pkgName2)) {
            return pkgName1;
        }

        StringBuilder sb = new StringBuilder();
        String[] split1 = pkgName1.split("\\.");
        String[] split2 = pkgName2.split("\\.");
        int minLen = split1.length;
        if (split1.length > split2.length) {
            minLen = split2.length;
        }

        for (int i = 0; i < minLen; i++) {
            if (split1[i].equals(split2[i])) {
                sb.append(split1[i]).append(".");
            }
        }

        if (sb.length() == 0 || !split1[0].equals(split2[0])) {
            return pkgName1;
        }

        String pkgName = sb.toString();
        if (pkgName.endsWith(".")) {
            return pkgName.substring(0, pkgName.length() - 1);
        }
        return pkgName;
    }
}
