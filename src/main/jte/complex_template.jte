@param String userName
@param String userEmail
@param java.util.List<String> hobbies

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
            color: #333;
        }
        .profile-card {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 400px;
            margin: auto;
        }
        .profile-header {
            text-align: center;
            border-bottom: 1px solid #eee;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
        .profile-header h1 {
            margin: 0;
            color: #5a5a5a;
        }
        .profile-content p {
            margin: 10px 0;
            line-height: 1.6;
        }
        .profile-content strong {
            color: #333;
        }
        .hobbies-list {
            list-style: none;
            padding: 0;
        }
        .hobbies-list li {
            background-color: #e7e7e7;
            border-radius: 4px;
            padding: 5px 10px;
            margin-bottom: 5px;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <div class="profile-card">
        <div class="profile-header">
            <h1>User Profile</h1>
        </div>
        <div class="profile-content">
            <p><strong>Name:</strong> ${userName}</p>
            <p><strong>Email:</strong> ${userEmail}</p>
            <p><strong>Hobbies:</strong></p>
            @if(!hobbies.isEmpty())
                <ul class="hobbies-list">
                    @for(String hobby : hobbies)
                        <li>${hobby}</li>
                    @endfor
                </ul>
            @else
                <p>No hobbies listed.</p>
            @endif
        </div>
    </div>
</body>
</html>