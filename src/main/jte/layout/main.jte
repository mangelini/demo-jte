@import java.time.format.DateTimeFormatter

@param gg.jte.Content content
@param String title
@param String logoBase64
@param java.time.LocalDateTime currentDate

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>JTE POC</title>
        <link rel="stylesheet" type="text/css" href="styles/style.css" />
    </head>
    <body class="body">
        <header class="header">
            <div class="image-wrapper">
                <img class="header-img" src="data:image/png;base64,${logoBase64}" alt="logo" />
            </div>
            <div class="header-info">
                <p>${title} - Q8 - ${currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
            </div>
            <div class="title">
                <h1>${title}</h1>
            </div>
        </header>

        <main>
            ${content}
        </main>
    </body>
</html>