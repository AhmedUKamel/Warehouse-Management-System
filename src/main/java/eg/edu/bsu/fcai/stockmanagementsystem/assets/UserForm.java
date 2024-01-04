package eg.edu.bsu.fcai.stockmanagementsystem.assets;

public record UserForm(
        String id,
        String nameArabic,
        String nameEnglish,
        String email,
        String password,
        String confirmPassword,
        String phone,
        String imageUrl
) {}
