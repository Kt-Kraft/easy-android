# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# BouncyCastle
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider

# Conscrypt
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier

# OpenJSSE
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Logback Android
# Issue #229
-keepclassmembers class ch.qos.logback.classic.pattern.* { <init>(); }

# Preserve Logback classes
-keep class ch.qos.logback.** { *; }
-keep class org.slf4j.** { *; }

# Keep all annotation attributes to ensure runtime annotations are not stripped.
-keepattributes *Annotation*

# If you don't use the mailing features of logback (i.e., the SMTPAppender),
# you might encounter an error while exporting your app with ProGuard.
# To resolve this, add the following rule:
-dontwarn ch.qos.logback.core.net.*

# create new printed all configuration on proguard/proguard-merged-config.txt for debugging
#-printconfiguration proguard-merged-config.txt
