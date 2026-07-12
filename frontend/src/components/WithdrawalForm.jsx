import { useEffect, useState } from "react";
import { withdraw } from "../services/withdrawalService";

export default function WithdrawalForm({
    investor,
    investorId,
    refresh
}) {

    const [amount, setAmount] = useState("");
    const [selectedProduct, setSelectedProduct] = useState("");

    const [success, setSuccess] = useState("");
    const [error, setError] = useState("");

    // Only display products that are currently eligible for withdrawal
    const availableProducts = investor?.products?.filter((product) => {

        if (
            product.productType === "RETIREMENT_ANNUITY" &&
            investor.age <= 65
        ) {
            return false;
        }

        return true;

    }) || [];

    useEffect(() => {

    if (availableProducts.length === 0) {
        setSelectedProduct("");
        return;
    }

    const exists = availableProducts.some(
        product => product.id === Number(selectedProduct)
    );

    if (!exists) {
        setSelectedProduct(availableProducts[0].id);
    }

}, [availableProducts, selectedProduct]);

    const handleSubmit = async (e) => {

        e.preventDefault();

        setSuccess("");
        setError("");

        if (!selectedProduct) {
            setError("No eligible investment is available for withdrawal.");
            return;
        }

        try {

            await withdraw({

                investorId,
                productId: Number(selectedProduct),
                amount: Number(amount)

            });

            setSuccess("Withdrawal completed successfully.");

            setAmount("");

            refresh();

        }

        catch (err) {

            setError(
                err.response?.data?.message ||
                "Unable to process withdrawal."
            );

        }

    };

    return (

        <div className="card">

            <h2>💸 Withdraw Funds</h2>

            {availableProducts.length === 0 ? (

                <div className="error">

                    No investment products are currently eligible for withdrawal.

                </div>

            ) : (

                <form onSubmit={handleSubmit}>

                    <div className="form-group">

                        <label>Select Investment</label>

                        <select
                            value={selectedProduct}
                            onChange={(e) => setSelectedProduct(e.target.value)}
                            required
                        >

                            {availableProducts.map((product) => (

                                <option
                                    key={product.id}
                                    value={product.id}
                                >

                                    {product.productName} (
                                    {product.productType
                                        .replaceAll("_", " ")
                                        .toLowerCase()
                                        .replace(/\b\w/g, c => c.toUpperCase())}
                                    )

                                </option>

                            ))}

                        </select>

                    </div>

                    <div className="form-group">

                        <label>Withdrawal Amount</label>

                        <input
                            type="number"
                            value={amount}
                            onChange={(e) => setAmount(e.target.value)}
                            placeholder="Enter amount"
                            min="0.01"
                            step="0.01"
                            required
                        />

                    </div>

                    <button
                        className="button"
                        type="submit"
                    >
                        Withdraw
                    </button>

                </form>

            )}

            {success && (

                <div className="success">
                    ✅ {success}
                </div>

            )}

            {error && (

                <div className="error">
                    ❌ {error}
                </div>

            )}

        </div>

    );

}
