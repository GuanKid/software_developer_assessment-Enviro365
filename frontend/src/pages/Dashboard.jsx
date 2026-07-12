import { useEffect, useState } from "react";

import Navbar from "../components/Navbar";
import PortfolioCard from "../components/PortfolioCard";
import ProductList from "../components/ProductList";
import WithdrawalForm from "../components/WithdrawalForm";
import WithdrawalHistory from "../components/WithdrawalHistory";

import { getInvestor } from "../services/investorService";
import { getWithdrawals } from "../services/withdrawalService";

const DEFAULT_INVESTOR_ID = 1;

export default function Dashboard() {

    const [investorId] = useState(DEFAULT_INVESTOR_ID);

    const [investor, setInvestor] = useState(null);
    const [withdrawals, setWithdrawals] = useState([]);
    const [loading, setLoading] = useState(true);

    async function loadData() {

        try {

            const investorResponse = await getInvestor(investorId);
            const withdrawalResponse = await getWithdrawals();

            setInvestor(investorResponse);
            setWithdrawals(withdrawalResponse);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }

    }

    useEffect(() => {

        loadData();

    }, [investorId]);

    if (loading) {

        return <h2 className="loading">Loading Dashboard...</h2>;

    }

    return (

        <>

            <Navbar />

            <div className="container">

                <div className="dashboard-grid">

                    <PortfolioCard investor={investor} />

                    <ProductList products={investor.products} />

                </div>

                <WithdrawalForm
                    investor={investor}
                    investorId={investorId}
                    refresh={loadData}
                />

                <WithdrawalHistory withdrawals={withdrawals} />

            </div>

        </>

    );

}
