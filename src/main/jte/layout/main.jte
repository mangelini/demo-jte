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
        @template.partials.header(
            title = title,
            logoBase64 = logoBase64,
            currentDate = currentDate
        )

        <main>
            ${content}
        </main>
    </body>
</html>