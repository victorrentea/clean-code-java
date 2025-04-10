using System.Globalization;

namespace Soat.CleanCode.VideoStore.Original
{
    public static class DecimalExtension
    {
        public static string ToOneDecimalString(this decimal thisAmount , CultureInfo cultureInfo = null)
        {
            cultureInfo = cultureInfo ?? CultureInfo.InvariantCulture;
            return thisAmount.ToString("0.0", cultureInfo);
        }
    }
}
