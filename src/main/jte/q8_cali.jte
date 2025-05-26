@param String logoBase64

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Prova Cali</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                font-size: 9px;
            }

            .table {
                width: 100%;
                border-collapse: collapse;
                border: 1px solid black;
                table-layout: auto;
            }

            .table td, .table th {
                padding: 8px;
                vertical-align: middle;
                text-align: left;
                border: 1px solid black;
            }

            .table .header-row td:nth-child(1) {
                text-align: left;
            }

            .table .header-row td:nth-child(2) {
                text-align: center;
            }

            .table .header-row td:nth-child(3) {
                text-align: right;
            }

            .table-content {
                margin-top: 50px;
            }
            
            .header-logo {
                max-width: 100px;
                height: auto;
                margin-left: auto;
            }

            .cell-blue {
                background-color: cornflowerblue;
            }

            .text-center {
                text-align: center;
            }

            .text-right {
                text-align: right;
            }

            h4 {
                margin: 0;
            }

        </style>
    </head>
    <body>
        <table class="table">
            <thead>
                <tr class="header-row">
                    <td>
                    </td>
                    <td class="text-center">
                        <h4 style="font-size: 14px">Q8 Scheda controllo giacenze</h4>
                    </td>
                    <td class="text-right">
                        <img src="data:image/png;base64,${logoBase64}" alt="logo" class="header-logo"/>
                    </td>
                </tr>
            </thead>
        </table>

        <table class="table table-content">
            <tbody>
                <tr>
                    <td class="cell-blue" colspan="5">Q8 Scheda controllo giacenze carburanti</td>
                    <td colspan="4">Namespace</td>
                    <td class="cell-blue">N°</td>
                    <td>ssCaliNumber</td>
                </tr>
                <tr>
                    <td class="cell-blue" colspan="4">Punto Vendita</td>
                    <td colspan="2"></td>
                    <td>ssPVCode</td>
                    <td>ssPVAddress</td>
                    <td>ssPVCity</td>
                    <td class="cell-blue">Data Rilevazione</td>
                    <td>SsDataInvioFormattedCo</td>
                </tr>
            <tr>
                <td class="cell-blue">Serbatoi BSP</td>
                <td class="cell-blue"></td>
                <td>ssNum</td>
                <td class="cell-blue">Serbatoi Gasolio</td>
                <td>ssNumSe</td>
                <td class="cell-blue">Serbatoi Benzina Prestazionale</td>
                <td>ssNumSerbQBE</td>
                <td class="cell-blue">Serbatoi Gasolio Prestazionale</td>
                <td>ssNum</td>
                <td class="cell-blue">Serbatoi GPL</td>
                <td>ssNumSerbGPL</td>
            </tr>
            </tbody>
        </table>
    </body>
</html>