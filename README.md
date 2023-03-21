# Currency rate

## Project Description:
The project is about creating a simple application for displaying live currency rates.

## Screenshot:
![Rates List](https://user-images.githubusercontent.com/41057771/225767086-a6d81929-2dae-4653-93ca-5e0741fcc335.png)
## Project Details:

### Story
User requests the currency rates.

### Use Case:

Primary course (happy path)

1. Execute “Load Rates” command using the URL. (it should execute every 2 minutes to refresh data).

2. System downloads data from url.

3. System validates downloaded data.

4. System create rate list from valid data.

5. System delivers rate list.

6. System show currency pair and its current price with maximum four decimals.

7. When the new price is higher than or equal to the previous one, it should be displayed in green with a green indicator (in the given assets), otherwise in red.

8. After “Load Rates” completion, system updates the updating date.


Invalid Data - error course (sad path)  

1. System delivers invalid-data error


No connectivity - error course (sad path)  

1. System delivers no-connectivity error


## Architecture
The Currency rate app follows the [official architecture guidance](https://developer.android.com/topic/architecture)

## Modularization
The Currency rate app has been fully modularized
