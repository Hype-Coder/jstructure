#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>


#define terminate_char(s,arg) (((char *) arg) [s] = '\0' );


int throwNoMemException (JNIEnv *env, const char *message);

jbyteArray copyFrom ( JNIEnv *env, void *value, int size );

void copyTo ( JNIEnv *env, jclass cls, void *value, jbyteArray element, int size );


JNIEXPORT jint JNICALL Java_template_Pointer_native_1malloc(JNIEnv *env, jobject obj, jint size){ // @suppress("Type cannot be resolved")

	void *ptr = (void*) malloc (size);

	if (ptr == NULL) return throwNoMemException (env,strerror(errno));

	return (unsigned long long int) ptr;

}


JNIEXPORT jlong JNICALL Java_template_Pointer_native_1calloc(JNIEnv *env, jobject obj, jint n, jint size){ // @suppress("Type cannot be resolved")

	void *ptr = (void*) calloc (n,size);

	if (ptr == NULL) return throwNoMemException (env,strerror(errno));

	return (unsigned long long int) ptr;

}


JNIEXPORT jlong JNICALL Java_template_Pointer_native_1realloc(JNIEnv *env, jobject obj, jlong pointer, jint size){ // @suppress("Type cannot be resolved")

	void *ptr = (void*) realloc ((void*) pointer,size);

	if (ptr == NULL) return throwNoMemException (env,strerror(errno));

	return (unsigned long long int) ptr;

}


JNIEXPORT jint JNICALL Java_template_Pointer_free(JNIEnv *env, jobject obj, jlong pointer){ free ((void*) pointer); } // @suppress("Type cannot be resolved")

JNIEXPORT jbyteArray JNICALL Java_template_Pointer_get(JNIEnv *env, jobject obj, jlong pointer, jint size){ return copyFrom (env, (void*) pointer, size); }// @suppress("Type cannot be resolved")

JNIEXPORT void JNICALL Java_template_Pointer_set(JNIEnv *env, jobject obj, jlong pointer, jbyteArray value, jint size){ copyTo (env, obj, (void*) pointer, value, size); }// @suppress("Type cannot be resolved")




jbyteArray copyFrom (JNIEnv *env, void *value, int len) { // @suppress("Type cannot be resolved")

    jbyte bytearray [ len ]; // @suppress("Type cannot be resolved")

    jbyteArray buffer = (*env) -> NewByteArray ( env, len ); // @suppress("Method cannot be resolved")

    memcpy ( bytearray, value, len );

    (*env) -> SetByteArrayRegion ( env, buffer, 0, len, bytearray );  // @suppress("Method cannot be resolved")

    return buffer ;

}


void copyTo (JNIEnv *env, jclass cls, void *value, jbyteArray element, int size) { // @suppress("Type cannot be resolved")

    int element_len = (*env) -> GetArrayLength (env,element); // @suppress("Method cannot be resolved")

    jbyte *bytearray = (*env) -> GetByteArrayElements ( env, element, NULL ); // @suppress("Method cannot be resolved") // @suppress("Type cannot be resolved")

    memcpy ( value, bytearray, size ); //copy byte array to char array

    terminate_char ( element_len, value );

    (*env) -> ReleaseByteArrayElements ( env, element , bytearray, JNI_ABORT ); //release byte array elements  // @suppress("Method cannot be resolved")

}



int throwNoMemException (JNIEnv *env, const char *message) {

	jclass exception_class = (*env) -> FindClass (env,"primitive/NoMemory"); // @suppress("Method cannot be resolved")

	return (*env) -> ThrowNew (env,exception_class,message); // @suppress("Method cannot be resolved")

}



