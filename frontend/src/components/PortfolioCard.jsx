import { formatCurrency } from "../utils/formatters";

export default function PortfolioCard({ investor }) {

    const hasRetirementAnnuity = investor.products.some(
        product => product.productType === "RETIREMENT_ANNUITY"
    );

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

            {hasRetirementAnnuity && (

                <div className="info-row">

                    <span>Retirement Annuity Eligibility</span>

                    <strong
                        className={
                            investor.age > 65
                                ? "eligible"
                                : "not-eligible"
                        }
                    >
                        {investor.age > 65
                            ? "Eligible"
                            : "Not Eligible"}
                    </strong>

                </div>

            )}

        </div>

    );

}
