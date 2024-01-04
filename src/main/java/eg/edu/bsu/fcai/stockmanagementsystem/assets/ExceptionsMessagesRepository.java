package eg.edu.bsu.fcai.stockmanagementsystem.assets;

public interface ExceptionsMessagesRepository {
    String USER_IN_USE = "لا يمكن حذف المستخدم وهو مسؤول عن مخازت او له سجل عمليات";
    String PASSWORD_DOES_NOT_MATCH = "كلمة المرور %s لا تطابق %s";
    String WEAK_PASSWORD = "كلمة المرور %s ضعيفة";
    String EXIST_PHONE = "رقم الهاتف %s مسجل بالفعل";
    String EXIST_ID = "الرقم القومي %s مسجل بالفعل";
    String EXIST_EMAIL = "البريد الأليكتروني %s مسجل بالفعل";
    String INVALID_PHONE = "رقم الهاتف %s غير صالح";
    String INVALID_ID = "الرقم القومي %s غير صالح";
    String INVALID_EMAIL = "البريد الأليكتروني %s غير صالح";
    String ID_NOT_FOUND = "لم يتم إيجاد مستخدم بالرقم القومي %s";
    String EMAIL_NOT_FOUND = "لم يتم إيجاد مستخدم بالبريد الألكتروني %s";
}
