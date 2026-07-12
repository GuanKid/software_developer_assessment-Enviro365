import { formatCurrency } from "../utils/formatters";

export default function ProductList({ products }) {

    return (

        <div className="card">

            <h2>📈 Investment Products</h2>

            <div className="product-grid">

                {products.map((product) => (

                    <div
                        className="product-card"
                        key={product.id}
                    >

                        <h3>{product.productName}</h3>

                        <small className="product-type">
    {product.productType
        .replaceAll("_", " ")
        .toLowerCase()
        .replace(/\b\w/g, c => c.toUpperCase())}
</small>

                        <p className="product-value">
                            {formatCurrency(product.value)}
                        </p>

                    </div>

                ))}

            </div>

        </div>

    );

}
