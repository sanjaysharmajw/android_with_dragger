package com.rv.tasktechstalwarts.common.remote.apis

/**
 * Rest end point for anonymous token
 */
const val loginWithEmail = "auth/user/login/"


/**
 * Multipart API parameter key
 */

const val NAME = "name"
const val COUNTRY_ID = "country_id"

/**
 * Https status code
 */
const val BAD_REQUEST = 400
const val UNAUTHORIZED_USER = 401
const val FORBIDDEN_ACCESS = 403
const val UNKNOWN_HOST_EXCEPTION = 500
const val HTTP_SUCCESS = 200
const val HTTP_CREATED = 201
const val HTTP_ACCEPTED = 202
const val HTTP_SERVICE_UNAVAILABLE = 503
const val HTTP_PARSE_RESPONSE = 505
const val TIMEOUT_30_SEC = 30L
const val TIMEOUT_60_SEC = 60L

/**
 * Rest JSON Keys
 */
const val GET_RECIPES = "recipes"
/**
 * Rest JSON Keys
 */
const val TOKEN_TYPE = "token_type"
const val REFRESH_TOKEN = "refresh_token"
const val SERVICE_NOT_AVAILABLE_MSG = "service not available"
const val PARSING_ERROR_MSG = "error parsing json"

/**
 * Bundle keys
 */
const val RECIPES_ITEM_MODEL = "recipes_item"


