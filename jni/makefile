CCOMPILER=gcc
JAVACOMPILER=javac

CLASS_PATH=../out/production/datastructures/
CLASS_PATH_JNI=../jni
CLASS_PATH_SRC=../src
CLASS_PATH_C = ../c

JHFLAGS=-h

CFLAGS = -fPIC -I "/usr/lib/jvm/java-1.11.0-openjdk-amd64/include/" -I "/usr/lib/jvm/java-1.11.0-openjdk-amd64/include/linux/"


all: libdatastructure.so

clean: rm $(CLASS_PATH_JNI)/libdatastructure.so $(CLASS_PATH_JNI)/template_Pointer.h


libdatastructure.so : Pointer.h
					gcc $(CFLAGS) -shared -o libdatastructure.so $(CLASS_PATH_C)/pointer.c


Pointer.h: $(CLASS_PATH)/template/Pointer.class
			javac -d $(CLASS_PATH) -classpath $(CLASS_PATH)/ -h $(CLASS_PATH_JNI) $(CLASS_PATH_SRC)/template/Pointer.java	
			
			
