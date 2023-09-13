<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyModel - PDF</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px;
        }

        th,
        td {
            text-align: left;
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        td.date {
            white-space: nowrap;
        }

        td.decimal {
            text-align: right;
        }

        .item-table {
            margin-top: 20px;
        }

        .item-table th {
            background-color: #333;
            color: white;
        }

        .item-table td {
            border-bottom: none;
        }

        .item-table td.price {
            text-align: right;
        }
    </style>

</head>

<body>

    <h1>MyModel List</h1>

    <div>
        <p>ID: ${myModel.id}</p>
        <p>Title: ${myModel.title}</p>
        <p>Description: ${myModel.description}</p>
        <p>Datetime: <span class="date">${myModel.fmtDateTime()}</span></p>
        <p>Total: <span class="decimal">${myModel.total?string.currency}</span></p>
        <h2>Items:</h2>
        <table class="item-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Sequence</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th class="price">Price</th>
                </tr>
            </thead>
            <tbody>
                <#list myModel.items as item>
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.sequence?string?left_pad(5, "0")}</td>
                        <td>${item.name}</td>
                        <td>${item.type}</td>
                        <td class="price">${item.price?string.currency}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
</body>

</html>