package br.com.productiva.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.efs.openreports.actions.admin.ListChartsAction;
import org.efs.openreports.objects.ReportChart;
import org.efs.openreports.providers.ChartProvider;
import org.efs.openreports.providers.ProviderException;

public class ListMapsAction extends ListChartsAction {
	
	private List reportCharts;

	private ChartProvider chartProvider;

	public List getReportCharts()
	{		
		return reportCharts;		
	}

	public String execute()
	{
		try
		{			
			List reportChartsAux = chartProvider.getReportCharts();
			reportCharts = new ArrayList();
			for (Iterator iterator = reportChartsAux.iterator(); iterator.hasNext();) {
				ReportChart type = (ReportChart) iterator.next();
				if (type.getChartType() == 5) {
					reportCharts.add(type);
				}
			}
		}
		catch(ProviderException pe)
		{
			addActionError(pe.getMessage());
			return ERROR;	
		}	

		return SUCCESS;
	}	

	public void setChartProvider(ChartProvider chartProvider)
	{
		this.chartProvider = chartProvider;
	}
}
