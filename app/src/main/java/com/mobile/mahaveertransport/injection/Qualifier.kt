package com.mobile.mahaveertransport.injection

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MovieListMappingAnnotation

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScopeIO

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScopeDefault