@import java.time.format.DateTimeFormatter

@param String title
@param String logoBase64
@param java.time.LocalDateTime currentDate

<header class="header">
    @if(logoBase64 != null)
        <div class="image-wrapper">
            <img class="header-img" src="data:image/png;base64,${logoBase64}" alt="logo" />
        </div>
    @endif
    <div class="header-info">
        <p>${title} - Q8 - ${currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
    </div>
    <div class="title">
        <h1>${title}</h1>
    </div>
</header>