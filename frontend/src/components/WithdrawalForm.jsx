import { useEffect, useState } from "react";
import { withdraw } from "../services/withdrawalService";

const INVESTOR_ID = 1;

export default function WithdrawalForm({ investor, refresh }) {

    const [amount, setAmount] = useState("");
    const [selectedProduct, setSelectedProduct] = useState("");

    const [success, setSuccess] = useState("");
    const [error, setError] = useState("");

    useEffect(() => {

        if (investor.products.length > 0) {
            setSelectedProduct(investor.products[0].id);
        }

    }, [investor]);

    const handleSubmit = async (e) => {

        e.preventDefault();

        setSuccess("");
        setError("");

        try {

            await withdraw({

                investorId: INVESTOR_ID,
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

            <form onSubmit={handleSubmit}>

                <div className="form-group">

                    <label>Select Investment</label>

                    <select
                        value={selectedProduct}
                        onChange={(e) => setSelectedProduct(e.target.value)}
                    >

                        {investor.products.map(product => (

                            <option
                                key={product.id}
                                value={product.id}
                            >

                                {product.productName} (
                                {product.productType.replaceAll("_", " ")}
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
                        onChange={(e)=>setAmount(e.target.value)}
                        placeholder="Enter amount"
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

            {success &&
                <div className="success">
                    ✅ {success}
                </div>
            }

            {error &&
                <div className="error">
                    ❌ {error}
                </div>
            }

        </div>

    );

}