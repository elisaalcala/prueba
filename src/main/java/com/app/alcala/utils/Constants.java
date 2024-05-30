package com.app.alcala.utils;

/**
 * Class containing microservice constants.
 */
public class Constants {
	public static final String DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	  // Características
    public static final String CHARACTERISTIC_NAME_CHANNEL = "Channel";
    public static final String CHARACTERISTIC_NAME_LOGIN = "Login";
    public static final String CHARACTERISTIC_NAME_CAMPAIGN_EXTERNAL_ID = "CampaignExternalId";
    public static final String CHARACTERISTIC_NAME_DELIVERY_DATE = "DeliveryDate";
    public static final String CHARACTERISTIC_NAME_COLLECTION_DATE = "CollectionDate";

    // Roles
    public static final String ROLE_MOBILE_BILLING_SYSTEM_CUSTOMER_ID = "MobileBillingSystemCustomerId";
    public static final String ROLE_MOBILE_BILLING_SYSTEM_ACCOUNT_ID = "MobileBillingSystemAccountId";
    public static final String ROLE_SFID = "SFID";
    public static final String ROLE_BUSINESS_NAME = "BusinessName";
    public static final String ROLE_BILLING_CUSTOMER = "BillingCustomer";

    // Tipos de medios
    public static final String MEDIUM_TYPE_PHONE = "Phone";
    public static final String MEDIUM_TYPE_EMAIL = "Email";
    public static final String MEDIUM_TYPE_ADDRESS = "Address";


    public static final String STATUS_CHANGE_TYPE_DELIVERY = "DeliveryCharge";
    
    // Tipos de precio
    public static final String PRICE_PRICE_TYPE_RECURRENT = "Recurrent";
    public static final String PRICE_PRICE_TYPE_INITIAL_CHARGE = "InitialCharge";
    public static final String PRICE_RECURRING_CHARGE_PERIOD_MONTHS = "Months";
    public static final String PRICE_NAME_SHIPPING_ORDER_ITEM_PRICE = "shippingOrderItemPrice";
    public static final String PRICE_DESCRIPTION_DELIVERY_CHARGE = "DeliveryCharge";
    public static final String PRICE_DESCRIPTION_PAYMENT_METHOD_CHARGE = "PaymentMethodCharge";

    // Tipos de envío
    public static final String ITEM_TYPE_DELIVERY = "Delivery";
    public static final String ITEM_TYPE_COLLECTION = "Collection";
    public static final String ITEM_NAME_SERVICE_ID = "ServiceId";
    public static final String ITEM_NAME_RELATED_DELIVERED_SHIPMENT_ITEM_ID = "RelatedDeliveredShipmentItemId";
    public static final String ITEM_NAME_COLLECTION_REASON = "CollectionReason";
    public static final String ITEM_NAME_FAILURE_NAME = "FailureName";
    public static final String ITEM_NAME_FAILURE_NOTES = "FailureNotes";
    
   

}