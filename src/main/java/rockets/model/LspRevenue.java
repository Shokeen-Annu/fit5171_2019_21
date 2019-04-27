package rockets.model;

import java.math.BigDecimal;
import java.util.Objects;

import static org.apache.commons.lang3.Validate.notNull;

public class LspRevenue extends Entity {

    private int year;
    private BigDecimal revenue;
    private LaunchServiceProvider lsp;

    public LspRevenue(int year, BigDecimal revenue,LaunchServiceProvider lsp)
    {
        notNull(revenue,"Revenue cannot be null");
        notNull(lsp,"Launch service provider cannot be null");
        validateYear(year);
        this.year = year;
        this.revenue = revenue;
        this.lsp = lsp;
    }

    public LaunchServiceProvider getLsp()
    {
        return lsp;
    }

    public int getYear()
    {
        return year;
    }

    public BigDecimal getRevenue()
    {
        return revenue;
    }

    public void setLsp(LaunchServiceProvider lsp)
    {
        notNull(lsp,"Launch service provider cannot be null");
        this.lsp = lsp;
    }

    public void setYear(int year)
    {
        validateYear(year);
        this.year = year;
    }

    public void addRevenue(BigDecimal price)
    {
        notNull(price,"Revenue cannot be null");
        this.revenue=this.revenue.add(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LspRevenue rev = (LspRevenue) o;
        return Objects.equals(year, rev.year) &&
                Objects.equals(lsp, rev.lsp);
    }

    @Override
    public String toString() {
        return "Revenue{" +
                "LSP='" + lsp.getName() + '\'' +
                ", Year='" + year + '\'' +
                ", Total Revenue='" + revenue + '\'' +
                '}';
    }
}
