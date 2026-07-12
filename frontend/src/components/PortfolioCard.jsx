import { formatCurrency } from "../utils/formatters";

export default function PortfolioCard({ investor }) {
    return (
        <div className="card">

            <h2>👤 Investor Portfolio</h2>

            <div className="info-row">
                <span>Name</span>
                <strong>{investor.name}</strong>
            </div>

            <div className="info-row">
                <span>Age</span>
                <strong>{investor.age}</strong>
            </div>

            <div className="info-row">
                <span>Available Balance</span>
                <strong className="balance">
                    {formatCurrency(investor.balance)}
                </strong>
            </div>

        </div>
    );
}