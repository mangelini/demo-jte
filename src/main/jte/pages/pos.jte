@import com.example.demojte.models.Product
@import java.time.format.DateTimeFormatter
@import java.util.List

@param String title
@param String logoBase64
@param java.time.LocalDateTime currentDate
@param String pointOfSale
@param java.time.LocalDate saleDate
@param String saleType
@param List<Product> products

@template.layout.main(content = @`
    <div class="point-of-sale-info">
        <p><b>Punto vendita:</b> ${pointOfSale}</p>
        <p><b>Data:</b> ${saleDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}</p>
        <p><b>Tipo:</b> ${saleType}</p>
        <p>Questo documento è stato generato automaticamente.</p>
    </div>
    <div class="products">
        <h2 class="products-title">Prodotti</h2>
        <table class="products-table">
            <thead>
                <tr>
                    <th>Codice</th>
                    <th>Descrizione</th>
                </tr>
            </thead>
            <tbody>
                @for (Product product : products)
                    <tr>
                        <td>${product.getCode()}</td>
                        <td>${product.getDescription()}</td>
                    </tr>
                @endfor
            </tbody>
        </table>
    </div>
`, title = title, logoBase64 = logoBase64, currentDate = currentDate)