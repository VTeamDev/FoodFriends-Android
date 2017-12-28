package com.vteam.foodfriends.utils

/**
 * Created by H2PhySicS on 12/11/2017.
 */
class Constant{
    companion object {
        val FIREBASE_PRODUCT = "products"
        val FIREBASE_PRODUCT_ADDRESS = "Address"
        val FIREBASE_PRODUCT_AVERATE_RATING = "AvgRating"
        val FIREBASE_PRODUCT_LAT = "Latitude"
        val FIREBASE_PRODUCT_LON = "Longitude"
        val FIREBASE_PRODUCT_NAME = "Name"
        val FIREBASE_PRODUCT_DISCOUNT = "Offers"
        val FIREBASE_PRODUCT_DISCOUNT_GROUP = "Group"
        val FIREBASE_PRODUCT_DISCOUNT_PAIR = "Pair"
        val FIREBASE_PRODUCT_PHOTOURL = "PhotoUrl"
        val FIREBASE_PRODUCT_TIME_OPEN = "TimeOpen"
        val FIREBASE_PRODUCT_TIME_CLOSE = "TimeClose"
        val FIREBASE_PRODUCT_CUISINE_URL = "ResUrlAlbums"
        val FIREBASE_PRODUCT_REVIEWS = "LstReview"

        val FIREBASE_USER = "users"
        val FIREBASE_USER_ID = "uid"
        val FIREBASE_USER_EMAIL = "email"
        val FIREBASE_USER_PASSWORD = "password"
        val FIREBASE_USER_USERNAME = "username"
        val FIREBASE_USER_PHONE = "phone"
        val FIREBASE_USER_DOB = "dob"
        val FIREBASE_USER_GENDER = "gender"
        val FIREBASE_USER_AVATAR = "avatar"
        val FIREBASE_USER_FOLLOWS = "follows"

        val FIREBASE_COMMENT_AUTHOR = "author"
        val FIREBASE_COMMENT_TITLE = "title"
        val FIREBASE_COMMENT_TIME = "time"
        val FIREBASE_COMMENT_CONTENT = "content"
        val FIREBASE_COMMENT_RATING = "rating"

        val FIREBASE_PENDING = "pendings"
        val FIREBASE_PENDING_PAIR = "pair"
        val FIREBASE_PENDING_GROUP = "group"
        val FIREBASE_PENDING_USER_CREATED_ID = "uid"
        val FIREBASE_PENDING_TIME = "time"
        val FIREBASE_PENDING_LAT = "lat"
        val FIREBASE_PENDING_LON = "lon"
        val FIREBASE_PENDING_MEMBERS = "members"

        val FIREBASE_MESSAGE = "messages"
        val FIREBASE_MESSAGE_CHAT ="chats"
        val FIREBASE_MESSAGE_CHAT_TITLE = "title"
        val FIREBASE_MESSAGE_CHAT_LAST_MESSAGE = "lastMessage"
        val FIREBASE_MESSAGE_CHAT_TIMESTAMP = "timestamp"
        val FIREBASE_MESSAGE_MEMBERS = "members"
        val FIREBASE_MESSAGE_MEMBERS_LIST = "list"
        val FIREBASE_MESSAGE_MESSAGES = "messages"
        val FIREBASE_MESSAGE_MESSAGES_CONTENT = "content"
        val FIREBASE_MESSAGE_MESSAGES_NAME = "name"
        val FIREBASE_MESSAGE_MESSAGES_MESSAGE = "message"
        val FIREBASE_MESSAGE_MESSAGES_TIMESTAMP = "timestamp"

        val EXTRA_RESTAURANT_ID = "resId"
        val EXTRA_ROOM_ID = "roomId"

        val PREF_NAME = "FoodFriendsPreferences"
        val PREF_TOKEN = "token"
        val PREF_TOKEN_EXPIRE = "token_expire"

    }
}