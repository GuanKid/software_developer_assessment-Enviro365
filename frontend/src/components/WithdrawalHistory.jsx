import { exportCsv } from "../services/withdrawalService";
import { formatCurrency } from "../utils/formatters";

export default function WithdrawalHistory({ withdrawals }) {
  return (
    <div className="card">
      <div className="table-header">
        <h2>📄 Withdrawal History</h2>

        <button
    className="button export-btn"
    onClick={exportCsv}
    disabled={withdrawals.length === 0}
>
    Download CSV
</button>
      </div>

      {withdrawals.length === 0 ? (
        <div className="empty-state">

    <h3>No Withdrawals Yet</h3>

    <p>
        Your completed withdrawal transactions will appear here.
    </p>

</div>
      ) : (
        <table>
          <thead>
            <tr>
              <th>Date</th>

              <th>Product</th>
              <th>Amount</th>

              <th>Remaining Balance</th>
            </tr>
          </thead>

          <tbody>
            {withdrawals.map((withdrawal) => (
              <tr key={withdrawal.id}>
                <td>{withdrawal.withdrawalDate}</td>

                <td>{withdrawal.productName}</td>

                <td>{formatCurrency(withdrawal.amount)}</td>

                <td>{formatCurrency(withdrawal.remainingBalance)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}
